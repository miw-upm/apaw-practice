package es.upm.miw.apaw.domain.services.stucktaskdetector;

import es.upm.miw.apaw.adapters.out.stucktaskdetector.postgres.StuckTaskRuleEntity;
import es.upm.miw.apaw.adapters.out.stucktaskdetector.postgres.StuckTaskRuleRepository;
import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.stucktaskdetector.StuckTaskRule;
import es.upm.miw.apaw.domain.model.stucktaskdetector.StuckTaskRuleCreation;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.StuckTaskDetectorSeederForDev.RULE_0;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class StuckTaskRuleServiceIT {
    private static final UserSnapshot USER = UserSnapshot.builder()
            .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000"))
            .mobile("600000100")
            .firstName("cliente0")
            .build();

    @Autowired
    private StuckTaskRuleService stuckTaskRuleService;
    @Autowired
    private StuckTaskRuleRepository stuckTaskRuleRepository;
    @MockitoBean
    private UserFinder userFinder;

    @Test
    void testCreate() {
        StuckTaskRuleCreation creation = this.newCreation();
        when(this.userFinder.read(USER.getId())).thenReturn(USER);

        StuckTaskRule created = this.stuckTaskRuleService.create(creation);

        assertThat(created.getId()).isNotNull();
        assertThat(created.getName()).isEqualTo(creation.getName());
        assertThat(created.getProcedureKeyword()).isEqualTo(creation.getProcedureKeyword());
        assertThat(created.getThresholdDays()).isEqualTo(creation.getThresholdDays());
        assertThat(created.getPenaltyAmount()).isEqualByComparingTo(creation.getPenaltyAmount());
        assertThat(created.getActive()).isTrue();
        assertThat(created.getCreatedAt()).isEqualTo(LocalDate.now());
        assertThat(created.getCreatedByUser()).isEqualTo(USER);
        StuckTaskRuleEntity entity = this.stuckTaskRuleRepository.findById(created.getId()).orElseThrow();
        assertThat(entity.getName()).isEqualTo(creation.getName());
        assertThat(entity.getCreatedByUserId()).isEqualTo(USER.getId());
    }

    @Test
    void testCreateCallsUserFinderOnce() {
        when(this.userFinder.read(USER.getId())).thenReturn(USER);

        this.stuckTaskRuleService.create(this.newCreation());

        verify(this.userFinder).read(USER.getId());
        verifyNoMoreInteractions(this.userFinder);
    }

    @Test
    void testCreateInactiveWithoutPenalty() {
        StuckTaskRuleCreation creation = this.newCreation();
        creation.setActive(false);
        creation.setPenaltyAmount(null);
        when(this.userFinder.read(USER.getId())).thenReturn(USER);

        StuckTaskRule created = this.stuckTaskRuleService.create(creation);

        assertThat(created.getActive()).isFalse();
        assertThat(created.getPenaltyAmount()).isNull();
    }

    @Test
    void testCreateDuplicateName() {
        StuckTaskRuleCreation creation = this.newCreation();
        creation.setName(RULE_0.getName());

        assertThatThrownBy(() -> this.stuckTaskRuleService.create(creation))
                .isInstanceOf(ConflictException.class).hasMessageContaining(RULE_0.getName());
        verifyNoInteractions(this.userFinder);
    }

    @Test
    void testCreateUserNotFound() {
        StuckTaskRuleCreation creation = this.newCreation();
        when(this.userFinder.read(USER.getId()))
                .thenThrow(new NotFoundException("User id not found: " + USER.getId()));

        assertThatThrownBy(() -> this.stuckTaskRuleService.create(creation))
                .isInstanceOf(NotFoundException.class).hasMessageContaining(USER.getId().toString());
        assertThat(this.stuckTaskRuleRepository.existsByName(creation.getName())).isFalse();
    }

    private StuckTaskRuleCreation newCreation() {
        return StuckTaskRuleCreation.builder()
                .name("IT rule " + UUID.randomUUID())
                .procedureKeyword("labour")
                .thresholdDays(20)
                .penaltyAmount(new BigDecimal("75.50"))
                .userId(USER.getId())
                .build();
    }
}
