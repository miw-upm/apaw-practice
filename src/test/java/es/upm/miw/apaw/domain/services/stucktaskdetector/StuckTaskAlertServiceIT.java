package es.upm.miw.apaw.domain.services.stucktaskdetector;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.stucktaskdetector.StuckTaskAlert;
import es.upm.miw.apaw.domain.model.stucktaskdetector.StuckTaskAlertCreation;
import es.upm.miw.apaw.domain.model.stucktaskdetector.StuckTaskAlertFindCriteria;
import es.upm.miw.apaw.domain.model.stucktaskdetector.StuckTaskAlertPatch;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.StuckTaskDetectorSeederForDev.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
class StuckTaskAlertServiceIT {
    private static final String RULE_USER = "stuckTaskRule.createdByUser";
    private static final String EMAIL_0 = "cliente0@example.com";
    private static final String EMAIL_1 = "cliente1@example.com";

    @MockitoBean
    private UserFinder userFinder;

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


    @Test
    void testFindWithoutCriteria() {
        this.mockUsers();

        List<StuckTaskAlert> alerts = this.stuckTaskAlertService.find(new StuckTaskAlertFindCriteria());

        assertThat(alerts).extracting(StuckTaskAlert::getId)
                .containsSubsequence(ALERT_ID_0, ALERT_ID_1, ALERT_ID_2, ALERT_ID_3, ALERT_ID_4);
        assertThat(alerts).filteredOn(alert -> alert.getId().equals(ALERT_ID_0))
                .singleElement()
                .satisfies(alert ->
                        assertThat(alert.getStuckTaskRule().getCreatedByUser().getEmail()).isEqualTo(EMAIL_0));
        verify(this.userFinder).findByIds(anySet());
        verifyNoMoreInteractions(this.userFinder);
    }

    @Test
    void testFindIgnoresBlankCriteria() {
        this.mockUsers();
        StuckTaskAlertFindCriteria criteria = StuckTaskAlertFindCriteria.builder()
                .procedureKeyword(" ").creatorEmail("").build();

        assertThat(this.findIds(criteria))
                .contains(ALERT_ID_0, ALERT_ID_1, ALERT_ID_2, ALERT_ID_3, ALERT_ID_4);
    }

    @Test
    void testFindByProcedureKeyword() {
        this.mockUsers();
        StuckTaskAlertFindCriteria criteria = StuckTaskAlertFindCriteria.builder()
                .procedureKeyword(RULE_1.getProcedureKeyword()).build();

        assertThat(this.findIds(criteria))
                .contains(ALERT_ID_2, ALERT_ID_3).doesNotContain(ALERT_ID_0, ALERT_ID_1, ALERT_ID_4);
    }

    @Test
    void testFindByWithPenalty() {
        this.mockUsers();

        assertThat(this.findIds(StuckTaskAlertFindCriteria.builder().withPenalty(true).build()))
                .contains(ALERT_ID_0, ALERT_ID_1, ALERT_ID_4).doesNotContain(ALERT_ID_2, ALERT_ID_3);
        assertThat(this.findIds(StuckTaskAlertFindCriteria.builder().withPenalty(false).build()))
                .contains(ALERT_ID_2, ALERT_ID_3).doesNotContain(ALERT_ID_0, ALERT_ID_1, ALERT_ID_4);
    }

    @Test
    void testFindByEscalated() {
        this.mockUsers();
        StuckTaskAlertFindCriteria criteria = StuckTaskAlertFindCriteria.builder().escalated(true).build();

        List<StuckTaskAlert> alerts = this.stuckTaskAlertService.find(criteria);

        assertThat(alerts).extracting(StuckTaskAlert::getId)
                .contains(ALERT_ID_1, ALERT_ID_3).doesNotContain(ALERT_ID_0, ALERT_ID_2, ALERT_ID_4);
        assertThat(alerts).allMatch(StuckTaskAlert::getEscalated);
    }

    @Test
    void testFindByCreatorEmail() {
        this.mockUsers();
        StuckTaskAlertFindCriteria criteria = StuckTaskAlertFindCriteria.builder().creatorEmail(EMAIL_1).build();

        assertThat(this.findIds(criteria))
                .contains(ALERT_ID_2, ALERT_ID_3).doesNotContain(ALERT_ID_0, ALERT_ID_1, ALERT_ID_4);
    }

    @Test
    void testFindByAllCriteria() {
        this.mockUsers();
        StuckTaskAlertFindCriteria criteria = StuckTaskAlertFindCriteria.builder()
                .procedureKeyword(RULE_0.getProcedureKeyword())
                .withPenalty(true)
                .escalated(true)
                .creatorEmail(EMAIL_0)
                .build();

        assertThat(this.findIds(criteria))
                .contains(ALERT_ID_1).doesNotContain(ALERT_ID_0, ALERT_ID_2, ALERT_ID_3, ALERT_ID_4);
    }

    @Test
    void testFindWithoutResultsDoesNotCallUserFinder() {
        StuckTaskAlertFindCriteria criteria = StuckTaskAlertFindCriteria.builder()
                .procedureKeyword("missing-" + UUID.randomUUID()).build();

        assertThat(this.stuckTaskAlertService.find(criteria)).isEmpty();
        verifyNoInteractions(this.userFinder);
    }

    @Test
    void testFindUserNotFound() {
        when(this.userFinder.findByIds(anySet())).thenReturn(List.of());

        assertThatThrownBy(() -> this.stuckTaskAlertService.find(new StuckTaskAlertFindCriteria()))
                .isInstanceOf(NotFoundException.class);
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

    private void mockUsers() {
        when(this.userFinder.findByIds(anySet())).thenAnswer(invocation -> {
            Set<UUID> ids = invocation.getArgument(0);
            return ids.stream()
                    .map(id -> UserSnapshot.builder().id(id).email(this.emailOf(id)).build())
                    .toList();
        });
    }

    private String emailOf(UUID userId) {
        if (userId.equals(RULE_0.getCreatedByUser().getId())) {
            return EMAIL_0;
        }
        if (userId.equals(RULE_1.getCreatedByUser().getId())) {
            return EMAIL_1;
        }
        return "other@example.com";
    }

    private List<UUID> findIds(StuckTaskAlertFindCriteria criteria) {
        return this.stuckTaskAlertService.find(criteria).stream().map(StuckTaskAlert::getId).toList();
    }
}