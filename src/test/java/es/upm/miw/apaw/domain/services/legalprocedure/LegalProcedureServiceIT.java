package es.upm.miw.apaw.domain.services.legalprocedure;

import es.upm.miw.apaw.adapters.out.legalprocedure.postgres.LegalProcedureEntity;
import es.upm.miw.apaw.adapters.out.legalprocedure.postgres.LegalProcedureRepository;
import es.upm.miw.apaw.adapters.out.legalprocedure.postgres.LegalTaskEntity;
import es.upm.miw.apaw.domain.models.UserSnapshot;
import es.upm.miw.apaw.domain.models.legalprocedure.CreationLegalProcedure;
import es.upm.miw.apaw.domain.models.legalprocedure.LegalProcedure;
import es.upm.miw.apaw.domain.models.legalprocedure.LegalProcedureFindCriteria;
import es.upm.miw.apaw.domain.models.legalprocedure.LegalTask;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.LegalTaskSeederForDev.ID_0;
import static es.upm.miw.apaw.config.seeders.LegalTaskSeederForDev.ID_1;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class LegalProcedureServiceIT {
    @Autowired
    private LegalProcedureService legalProcedureService;
    @Autowired
    private LegalProcedureRepository legalProcedureRepository;
    @MockitoBean
    private UserFinder userFinder;

    @Test
    @Transactional
    void testCreate() {
        UserSnapshot user = UserSnapshot.builder()
                .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000"))
                .mobile("600000100")
                .firstName("cliente0")
                .build();
        CreationLegalProcedure creation = CreationLegalProcedure.builder()
                .title("Legal procedure " + UUID.randomUUID())
                .budget(BigDecimal.TEN)
                .legalTaskIds(List.of(ID_0, ID_1))
                .userId(user.getId())
                .build();
        when(this.userFinder.read(user.getId())).thenReturn(user);

        LegalProcedure legalProcedure = this.legalProcedureService.create(creation);

        assertThat(legalProcedure.getId()).isNotNull();
        assertThat(legalProcedure.getStartedDate()).isEqualTo(LocalDate.now());
        assertThat(legalProcedure.getVatIncluded()).isFalse();
        assertThat(legalProcedure.getLegalTasks()).extracting(LegalTask::getId).containsExactly(ID_0, ID_1);
        assertThat(legalProcedure.getUserSnapshot()).isEqualTo(user);
        LegalProcedureEntity entity = this.legalProcedureRepository.findById(legalProcedure.getId()).orElseThrow();
        assertThat(entity.getTitle()).isEqualTo(creation.getTitle());
        assertThat(entity.getLegalTasks()).extracting(LegalTaskEntity::getId).containsExactly(ID_0, ID_1);
        assertThat(entity.getUserId()).isEqualTo(user.getId());
    }

    @Test
    @Transactional
    void testFindByUserMobile() {
        UserSnapshot firstUser = UserSnapshot.builder()
                .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000"))
                .mobile("600000100")
                .firstName("cliente0")
                .build();
        UserSnapshot secondUser = UserSnapshot.builder()
                .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0001"))
                .mobile("600000101")
                .firstName("cliente1")
                .build();
        when(this.userFinder.read(firstUser.getId())).thenReturn(firstUser);
        when(this.userFinder.read(secondUser.getId())).thenReturn(secondUser);
        LegalProcedure first = this.legalProcedureService.create(this.creation(firstUser.getId()));
        LegalProcedure second = this.legalProcedureService.create(this.creation(secondUser.getId()));
        when(this.userFinder.findByIds(Set.of(firstUser.getId(), secondUser.getId())))
                .thenReturn(List.of(firstUser, secondUser));

        List<LegalProcedure> legalProcedures = this.legalProcedureService.find(
                LegalProcedureFindCriteria.builder().vatIncluded(true).userMobile(firstUser.getMobile()).build());

        assertThat(legalProcedures).extracting(LegalProcedure::getId)
                .contains(first.getId()).doesNotContain(second.getId());
        assertThat(legalProcedures).filteredOn(legalProcedure -> legalProcedure.getId().equals(first.getId()))
                .singleElement().extracting(LegalProcedure::getUserSnapshot).isEqualTo(firstUser);
    }

    private CreationLegalProcedure creation(UUID userId) {
        return CreationLegalProcedure.builder()
                .title("Legal procedure " + UUID.randomUUID())
                .budget(BigDecimal.TEN)
                .vatIncluded(true)
                .legalTaskIds(List.of(ID_0))
                .userId(userId)
                .build();
    }
}
