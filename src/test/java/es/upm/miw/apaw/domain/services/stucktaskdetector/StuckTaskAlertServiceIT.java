package es.upm.miw.apaw.domain.services.stucktaskdetector;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.stucktaskdetector.StuckTaskAlert;
import es.upm.miw.apaw.domain.model.stucktaskdetector.StuckTaskAlertCreation;
import es.upm.miw.apaw.domain.model.stucktaskdetector.StuckTaskAlertPatch;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.StuckTaskDetectorSeederForDev.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
class StuckTaskAlertServiceIT {
    private static final String RULE_USER = "stuckTaskRule.createdByUser";

    @Autowired
    private StuckTaskAlertService stuckTaskAlertService;

    @Test
    void testReadSeeder() {
        StuckTaskAlert alert = this.stuckTaskAlertService.read(ALERT_ID_0);
        assertThat(alert).usingRecursiveComparison().ignoringFields(RULE_USER).isEqualTo(ALERT_0);
        assertThat(alert.getStuckTaskRule().getCreatedByUser().getId())
                .isEqualTo(RULE_0.getCreatedByUser().getId());
    }

    @Test
    void testReadNotFound() {
        UUID id = UUID.randomUUID();
        assertThatThrownBy(() -> this.stuckTaskAlertService.read(id))
                .isInstanceOf(NotFoundException.class).hasMessageContaining(id.toString());
    }

    @Test
    void testFindAllAllowsAdditionalAlerts() {
        StuckTaskAlert extra = this.createAlert();
        List<StuckTaskAlert> alerts = this.stuckTaskAlertService.findAll();
        assertThat(alerts).extracting(StuckTaskAlert::getId)
                .contains(extra.getId())
                .containsSubsequence(ALERT_ID_0, ALERT_ID_1, ALERT_ID_2, ALERT_ID_3, ALERT_ID_4);
        assertThat(this.stuckTaskAlertService.findAll()).extracting(StuckTaskAlert::getId)
                .containsExactlyElementsOf(alerts.stream().map(StuckTaskAlert::getId).toList());
    }

    @Test
    void testCreate() {
        StuckTaskAlertCreation creation = this.newCreation();
        StuckTaskAlert created = this.stuckTaskAlertService.create(creation);
        assertThat(created.getId()).isNotNull();
        assertThat(created.getReference()).isEqualTo(creation.getReference());
        assertThat(created.getDetectedAt()).isEqualTo(LocalDate.now());
        assertThat(created.getEscalated()).isFalse();
        assertThat(created.getResolvedAt()).isNull();
        assertThat(created.getResolutionNotes()).isNull();
        assertThat(created.getStuckTaskRule().getId()).isEqualTo(RULE_ID_0);
        assertThat(this.stuckTaskAlertService.read(created.getId())).usingRecursiveComparison().isEqualTo(created);
    }

    @Test
    void testCreateDuplicateReference() {
        StuckTaskAlertCreation creation = this.newCreation();
        creation.setReference(ALERT_0.getReference());
        assertThatThrownBy(() -> this.stuckTaskAlertService.create(creation))
                .isInstanceOf(ConflictException.class).hasMessageContaining(ALERT_0.getReference());
    }

    @Test
    void testCreateWithoutReferenceTwice() {
        StuckTaskAlertCreation creation = StuckTaskAlertCreation.builder().stuckTaskRuleId(RULE_ID_0).build();
        assertThat(this.stuckTaskAlertService.create(creation).getId())
                .isNotEqualTo(this.stuckTaskAlertService.create(creation).getId());
    }

    @Test
    void testCreateStuckTaskRuleNotFound() {
        StuckTaskAlertCreation creation = this.newCreation();
        creation.setStuckTaskRuleId(UUID.randomUUID());
        assertThatThrownBy(() -> this.stuckTaskAlertService.create(creation))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining(creation.getStuckTaskRuleId().toString());
    }

    @Test
    void testUpdateReplacesUpdatableFields() {
        StuckTaskAlert original = this.createAlert();
        StuckTaskAlert replacement = StuckTaskAlert.builder()
                .resolvedAt(LocalDate.of(2026, 2, 1)).escalated(true).resolutionNotes("Resolved").build();
        this.stuckTaskAlertService.update(original.getId(), replacement);
        StuckTaskAlert updated = this.stuckTaskAlertService.read(original.getId());
        assertThat(updated.getReference()).isNull();
        assertThat(updated.getResolvedAt()).isEqualTo(LocalDate.of(2026, 2, 1));
        assertThat(updated.getEscalated()).isTrue();
        assertThat(updated.getResolutionNotes()).isEqualTo("Resolved");
        assertThat(updated.getDetectedAt()).isEqualTo(original.getDetectedAt());
    }

    @Test
    void testUpdateKeepsStuckTaskRule() {
        StuckTaskAlert original = this.createAlert();
        StuckTaskAlert replacement = StuckTaskAlert.builder()
                .reference(original.getReference()).stuckTaskRule(RULE_1).build();
        this.stuckTaskAlertService.update(original.getId(), replacement);
        assertThat(this.stuckTaskAlertService.read(original.getId()).getStuckTaskRule().getId())
                .isEqualTo(RULE_ID_0);
    }

    @Test
    void testUpdateSameReference() {
        StuckTaskAlert alert = this.createAlert();
        alert.setResolutionNotes("Same reference");
        this.stuckTaskAlertService.update(alert.getId(), alert);
        assertThat(this.stuckTaskAlertService.read(alert.getId()).getResolutionNotes()).isEqualTo("Same reference");
    }

    @Test
    void testUpdateNotFound() {
        UUID id = UUID.randomUUID();
        assertThatThrownBy(() -> this.stuckTaskAlertService.update(id, ALERT_0))
                .isInstanceOf(NotFoundException.class).hasMessageContaining(id.toString());
    }

    @Test
    void testUpdateDuplicateReferenceLeavesAlertUnchanged() {
        StuckTaskAlert alert = this.createAlert();
        StuckTaskAlert replacement = StuckTaskAlert.builder().reference(ALERT_0.getReference()).escalated(true).build();
        assertThatThrownBy(() -> this.stuckTaskAlertService.update(alert.getId(), replacement))
                .isInstanceOf(ConflictException.class).hasMessageContaining(ALERT_0.getReference());
        assertThat(this.stuckTaskAlertService.read(alert.getId())).usingRecursiveComparison().isEqualTo(alert);
    }

    @Test
    void testPatchOnlyPresentFields() {
        StuckTaskAlert original = this.createAlert();
        this.stuckTaskAlertService.patch(original.getId(), new StuckTaskAlertPatch(null, null, true, null));
        StuckTaskAlert patched = this.stuckTaskAlertService.read(original.getId());
        assertThat(patched.getEscalated()).isTrue();
        assertThat(patched).usingRecursiveComparison().ignoringFields("escalated").isEqualTo(original);
    }

    @Test
    void testPatchSameReference() {
        StuckTaskAlert original = this.createAlert();
        this.stuckTaskAlertService.patch(original.getId(),
                new StuckTaskAlertPatch(original.getReference(), null, null, "Patched notes"));
        assertThat(this.stuckTaskAlertService.read(original.getId()).getResolutionNotes()).isEqualTo("Patched notes");
    }

    @Test
    void testPatchNotFound() {
        UUID id = UUID.randomUUID();
        StuckTaskAlertPatch patch = new StuckTaskAlertPatch(null, null, true, null);
        assertThatThrownBy(() -> this.stuckTaskAlertService.patch(id, patch))
                .isInstanceOf(NotFoundException.class).hasMessageContaining(id.toString());
    }

    @Test
    void testPatchDuplicateReferenceLeavesAlertUnchanged() {
        StuckTaskAlert alert = this.createAlert();
        StuckTaskAlertPatch patch = new StuckTaskAlertPatch(ALERT_0.getReference(), null, true, null);
        assertThatThrownBy(() -> this.stuckTaskAlertService.patch(alert.getId(), patch))
                .isInstanceOf(ConflictException.class).hasMessageContaining(ALERT_0.getReference());
        assertThat(this.stuckTaskAlertService.read(alert.getId())).usingRecursiveComparison().isEqualTo(alert);
    }

    @Test
    void testDelete() {
        StuckTaskAlert alert = this.createAlert();
        this.stuckTaskAlertService.delete(alert.getId());
        assertThatThrownBy(() -> this.stuckTaskAlertService.read(alert.getId())).isInstanceOf(NotFoundException.class);
    }

    @Test
    void testDeleteNotFound() {
        UUID id = UUID.randomUUID();
        assertThatThrownBy(() -> this.stuckTaskAlertService.delete(id))
                .isInstanceOf(NotFoundException.class).hasMessageContaining(id.toString());
    }

    private StuckTaskAlertCreation newCreation() {
        return StuckTaskAlertCreation.builder()
                .reference("IT-" + UUID.randomUUID())
                .stuckTaskRuleId(RULE_ID_0)
                .build();
    }

    private StuckTaskAlert createAlert() {
        return this.stuckTaskAlertService.create(this.newCreation());
    }
}