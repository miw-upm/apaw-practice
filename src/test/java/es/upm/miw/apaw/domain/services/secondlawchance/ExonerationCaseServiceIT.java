package es.upm.miw.apaw.domain.services.secondlawchance;

import es.upm.miw.apaw.adapters.out.secondlawchance.postgres.DebtEntity;
import es.upm.miw.apaw.adapters.out.secondlawchance.postgres.ExonerationCaseEntity;
import es.upm.miw.apaw.adapters.out.secondlawchance.postgres.ExonerationCaseRepository;
import es.upm.miw.apaw.domain.exceptions.BadRequestException;
import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.secondlawchance.CreationExonerationCase;
import es.upm.miw.apaw.domain.model.secondlawchance.Debt;
import es.upm.miw.apaw.domain.model.secondlawchance.ExonerationCase;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.SecondLawChanceSeederForDev.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class ExonerationCaseServiceIT {
    @Autowired
    private ExonerationCaseService exonerationCaseService;
    @Autowired
    private ExonerationCaseRepository exonerationCaseRepository;
    @MockitoBean
    private UserFinder userFinder;

    @Test
    @Transactional
    void testCreate() {
        UserSnapshot user = this.user();
        CreationExonerationCase creation = this.buildCreation().build();
        when(this.userFinder.read(USER_ID_0)).thenReturn(user);

        ExonerationCase exonerationCase = this.exonerationCaseService.create(creation);

        assertThat(exonerationCase.getId()).isNotNull();
        assertThat(exonerationCase.getFilingDate()).isEqualTo(LocalDate.now());
        assertThat(exonerationCase.getCaseNumber()).isEqualTo(creation.getCaseNumber());
        assertThat(exonerationCase.getLawyer()).isEqualTo("IT lawyer");
        assertThat(exonerationCase.getDebts()).extracting(Debt::getId).containsExactly(DEBT_ID_0, DEBT_ID_1);
        assertThat(exonerationCase.getUserSnapshot()).isEqualTo(user);
        ExonerationCaseEntity entity = this.exonerationCaseRepository.findById(exonerationCase.getId())
                .orElseThrow();
        assertThat(entity.getCaseNumber()).isEqualTo(creation.getCaseNumber());
        assertThat(entity.getDebts()).extracting(DebtEntity::getId).containsExactly(DEBT_ID_0, DEBT_ID_1);
        assertThat(entity.getUserId()).isEqualTo(USER_ID_0);
    }

    @Test
    void testCreateWithoutLawyerNorResolutionDate() {
        when(this.userFinder.read(USER_ID_0)).thenReturn(this.user());

        ExonerationCase exonerationCase = this.exonerationCaseService.create(
                this.buildCreation().lawyer(null).resolutionDate(null).build());

        assertThat(exonerationCase.getLawyer()).isNull();
        assertThat(exonerationCase.getResolutionDate()).isNull();
        assertThat(this.exonerationCaseRepository.existsById(exonerationCase.getId())).isTrue();
    }

    @Test
    void testCreateWithFutureResolutionDate() {
        when(this.userFinder.read(USER_ID_0)).thenReturn(this.user());
        LocalDate resolutionDate = LocalDate.now().plusMonths(6);

        ExonerationCase exonerationCase = this.exonerationCaseService.create(
                this.buildCreation().resolutionDate(resolutionDate).build());

        assertThat(exonerationCase.getResolutionDate()).isEqualTo(resolutionDate);
    }

    @Test
    void testCreateWithDebtAlreadyInAnotherCase() {
        when(this.userFinder.read(USER_ID_0)).thenReturn(this.user());
        assertThat(this.exonerationCaseRepository.existsByDebtsId(DEBT_ID_0)).isTrue();

        ExonerationCase exonerationCase = this.exonerationCaseService.create(
                this.buildCreation().debtIds(List.of(DEBT_ID_0)).build());

        assertThat(exonerationCase.getDebts()).extracting(Debt::getId).containsExactly(DEBT_ID_0);
        assertThat(this.exonerationCaseRepository.existsById(exonerationCase.getId())).isTrue();
    }

    @Test
    void testCreateDuplicateCaseNumber() {
        CreationExonerationCase creation = this.buildCreation().caseNumber(CASE_NUMBER_0).build();
        assertThatThrownBy(() -> this.exonerationCaseService.create(creation))
                .isInstanceOf(ConflictException.class).hasMessageContaining(CASE_NUMBER_0);
        verifyNoInteractions(this.userFinder);
    }

    @Test
    void testCreatePastResolutionDate() {
        LocalDate resolutionDate = LocalDate.now().minusDays(1);
        CreationExonerationCase creation = this.buildCreation().resolutionDate(resolutionDate).build();
        assertThatThrownBy(() -> this.exonerationCaseService.create(creation))
                .isInstanceOf(BadRequestException.class).hasMessageContaining(resolutionDate.toString());
        assertThat(this.exonerationCaseRepository.existsByCaseNumber(creation.getCaseNumber())).isFalse();
        verifyNoInteractions(this.userFinder);
    }

    @Test
    void testCreateDebtNotFound() {
        UUID missingId = UUID.randomUUID();
        CreationExonerationCase creation = this.buildCreation().debtIds(List.of(DEBT_ID_0, missingId)).build();
        assertThatThrownBy(() -> this.exonerationCaseService.create(creation))
                .isInstanceOf(NotFoundException.class).hasMessageContaining(missingId.toString());
        assertThat(this.exonerationCaseRepository.existsByCaseNumber(creation.getCaseNumber())).isFalse();
        verifyNoInteractions(this.userFinder);
    }

    @Test
    void testCreateRepeatedDebtId() {
        CreationExonerationCase creation = this.buildCreation().debtIds(List.of(DEBT_ID_0, DEBT_ID_0)).build();
        assertThatThrownBy(() -> this.exonerationCaseService.create(creation))
                .isInstanceOf(BadRequestException.class).hasMessageContaining(DEBT_ID_0.toString());
        assertThat(this.exonerationCaseRepository.existsByCaseNumber(creation.getCaseNumber())).isFalse();
        verifyNoInteractions(this.userFinder);
    }

    @Test
    void testCreateUserNotFound() {
        CreationExonerationCase creation = this.buildCreation().build();
        when(this.userFinder.read(USER_ID_0))
                .thenThrow(new NotFoundException("Not found on read user by id " + USER_ID_0));
        assertThatThrownBy(() -> this.exonerationCaseService.create(creation))
                .isInstanceOf(NotFoundException.class).hasMessageContaining(USER_ID_0.toString());
        assertThat(this.exonerationCaseRepository.existsByCaseNumber(creation.getCaseNumber())).isFalse();
    }

    private CreationExonerationCase.CreationExonerationCaseBuilder buildCreation() {
        return CreationExonerationCase.builder()
                .caseNumber("IT-CASE-" + UUID.randomUUID())
                .lawyer("IT lawyer")
                .debtIds(List.of(DEBT_ID_0, DEBT_ID_1))
                .userId(USER_ID_0);
    }

    private UserSnapshot user() {
        return UserSnapshot.builder()
                .id(USER_ID_0)
                .mobile("600000100")
                .firstName("cliente0")
                .build();
    }
}
