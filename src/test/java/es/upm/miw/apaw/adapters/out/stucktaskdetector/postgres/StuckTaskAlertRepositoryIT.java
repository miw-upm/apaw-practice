package es.upm.miw.apaw.adapters.out.stucktaskdetector.postgres;

import es.upm.miw.apaw.domain.model.stucktaskdetector.StuckTaskAlert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.StuckTaskDetectorSeederForDev.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
class StuckTaskAlertRepositoryIT {
    @Autowired
    private StuckTaskAlertRepository stuckTaskAlertRepository;
    @Autowired
    private StuckTaskRuleRepository stuckTaskRuleRepository;

    @Test
    void testConversionKeepsIdentityAndFields() {
        StuckTaskAlert converted = new StuckTaskAlertEntity(ALERT_0).toDomain();
        assertThat(converted).usingRecursiveComparison()
                .ignoringFields("stuckTaskRule.createdByUser").isEqualTo(ALERT_0);
        assertThat(converted.getStuckTaskRule().getCreatedByUser().getId())
                .isEqualTo(RULE_0.getCreatedByUser().getId());
    }

    @Test
    void testExistsByReference() {
        assertThat(this.stuckTaskAlertRepository.existsByReference(ALERT_0.getReference())).isTrue();
        assertThat(this.stuckTaskAlertRepository.existsByReference("missing-" + UUID.randomUUID())).isFalse();
    }

    @Test
    void testFindAllByOrderByDetectedAtAscIdAsc() {
        List<StuckTaskAlertEntity> alerts = this.stuckTaskAlertRepository.findAllByOrderByDetectedAtAscIdAsc();
        assertThat(alerts).extracting(StuckTaskAlertEntity::getId)
                .containsSubsequence(ALERT_ID_0, ALERT_ID_1, ALERT_ID_2, ALERT_ID_3, ALERT_ID_4);
        assertThat(alerts).filteredOn(alert -> alert.getId().equals(ALERT_ID_0))
                .extracting(alert -> alert.getStuckTaskRule().getName()).containsExactly(RULE_0.getName());
    }

    @Test
    void testUniqueReferenceIsEnforced() {
        StuckTaskAlertEntity duplicate = this.newEntity();
        duplicate.setReference(ALERT_0.getReference());
        assertThatThrownBy(() -> this.stuckTaskAlertRepository.saveAndFlush(duplicate))
                .isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    void testNullReferenceIsAllowedTwice() {
        StuckTaskAlertEntity first = this.newEntity();
        StuckTaskAlertEntity second = this.newEntity();
        this.stuckTaskAlertRepository.saveAndFlush(first);
        this.stuckTaskAlertRepository.saveAndFlush(second);
        assertThat(this.stuckTaskAlertRepository.existsById(first.getId())).isTrue();
        assertThat(this.stuckTaskAlertRepository.existsById(second.getId())).isTrue();
    }

    @Test
    void testDeleteKeepsStuckTaskRule() {
        StuckTaskAlertEntity alert = this.stuckTaskAlertRepository.saveAndFlush(this.newEntity());
        this.stuckTaskAlertRepository.deleteById(alert.getId());
        assertThat(this.stuckTaskAlertRepository.existsById(alert.getId())).isFalse();
        assertThat(this.stuckTaskRuleRepository.existsById(RULE_ID_0)).isTrue();
    }

    private StuckTaskAlertEntity newEntity() {
        return StuckTaskAlertEntity.builder().id(UUID.randomUUID())
                .detectedAt(LocalDate.of(2026, 1, 10)).escalated(false)
                .stuckTaskRule(this.stuckTaskRuleRepository.findById(RULE_ID_0).orElseThrow()).build();
    }
}
