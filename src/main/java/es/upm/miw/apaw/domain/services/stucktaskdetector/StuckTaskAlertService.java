package es.upm.miw.apaw.domain.services.stucktaskdetector;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.stucktaskdetector.*;
import es.upm.miw.apaw.domain.ports.out.stucktaskdetector.StuckTaskAlertGateway;
import es.upm.miw.apaw.domain.ports.out.stucktaskdetector.StuckTaskRuleGateway;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StuckTaskAlertService {

    private final StuckTaskAlertGateway stuckTaskAlertGateway;
    private final StuckTaskRuleGateway stuckTaskRuleGateway;
    private final UserFinder userFinder;

    public StuckTaskAlert create(StuckTaskAlertCreation creation) {
        this.assertReferenceNotExists(creation.getReference());
        StuckTaskRule stuckTaskRule = this.stuckTaskRuleGateway.read(creation.getStuckTaskRuleId())
                .orElseThrow(() -> new NotFoundException(
                        "Stuck task rule id not found: " + creation.getStuckTaskRuleId()));

        StuckTaskAlert stuckTaskAlert = StuckTaskAlert.builder()
                .reference(creation.getReference())
                .stuckTaskRule(stuckTaskRule)
                .build();
        stuckTaskAlert.doDefault();
        return this.stuckTaskAlertGateway.create(stuckTaskAlert);
    }

    public StuckTaskAlert read(UUID id) {
        return this.stuckTaskAlertGateway.read(id)
                .orElseThrow(() -> new NotFoundException("Stuck task alert id not found: " + id));
    }

    private void assertReferenceNotExists(String reference) {
        if (reference != null && this.stuckTaskAlertGateway.existsByReference(reference)) {
            throw new ConflictException("Stuck task alert reference already exists: " + reference);
        }
    }

    public StuckTaskAlert update(UUID id, StuckTaskAlert stuckTaskAlert) {
        StuckTaskAlert stored = this.read(id);
        if (!Objects.equals(stored.getReference(), stuckTaskAlert.getReference())) {
            this.assertReferenceNotExists(stuckTaskAlert.getReference());
        }
        stored.setReference(stuckTaskAlert.getReference());
        stored.setResolvedAt(stuckTaskAlert.getResolvedAt());
        stored.setEscalated(Boolean.TRUE.equals(stuckTaskAlert.getEscalated()));
        stored.setResolutionNotes(stuckTaskAlert.getResolutionNotes());
        return this.stuckTaskAlertGateway.update(stored);
    }

    private StuckTaskRule readStuckTaskRule(UUID id) {
        return this.stuckTaskRuleGateway.read(id)
                .orElseThrow(() -> new NotFoundException("Stuck task rule id not found: " + id));
    }

    public void delete(UUID id) {
        this.read(id);
        this.stuckTaskAlertGateway.delete(id);
    }

    public List<StuckTaskAlert> findAll() {
        return this.stuckTaskAlertGateway.findAll();
    }

    public StuckTaskAlert patch(UUID id, StuckTaskAlertPatch patch) {
        StuckTaskAlert stored = this.read(id);
        if (patch.reference() != null) {
            if (!patch.reference().equals(stored.getReference())) {
                this.assertReferenceNotExists(patch.reference());
            }
            stored.setReference(patch.reference());
        }
        if (patch.resolvedAt() != null) {
            stored.setResolvedAt(patch.resolvedAt());
        }
        if (patch.escalated() != null) {
            stored.setEscalated(patch.escalated());
        }
        if (patch.resolutionNotes() != null) {
            stored.setResolutionNotes(patch.resolutionNotes());
        }
        return this.stuckTaskAlertGateway.update(stored);
    }

    public List<StuckTaskAlert> find(StuckTaskAlertFindCriteria criteria) {
        List<StuckTaskAlert> stuckTaskAlerts = this.stuckTaskAlertGateway.find(criteria);
        if (stuckTaskAlerts.isEmpty()) {
            return List.of();
        }
        Set<UUID> userIds = stuckTaskAlerts.stream()
                .map(stuckTaskAlert -> stuckTaskAlert.getStuckTaskRule().getCreatedByUser().getId())
                .collect(Collectors.toSet());
        Map<UUID, UserSnapshot> usersById = this.userFinder.findByIds(userIds).stream()
                .collect(Collectors.toMap(UserSnapshot::getId, Function.identity()));
        return stuckTaskAlerts.stream()
                .map(stuckTaskAlert -> this.enrichRuleCreator(stuckTaskAlert, usersById))
                .filter(stuckTaskAlert -> this.matchesCreatorEmail(criteria, stuckTaskAlert))
                .toList();
    }

    private StuckTaskAlert enrichRuleCreator(StuckTaskAlert stuckTaskAlert, Map<UUID, UserSnapshot> usersById) {
        StuckTaskRule stuckTaskRule = stuckTaskAlert.getStuckTaskRule();
        UUID userId = stuckTaskRule.getCreatedByUser().getId();
        UserSnapshot user = usersById.get(userId);
        if (user == null) {
            throw new NotFoundException("User id not found: " + userId);
        }
        stuckTaskRule.setCreatedByUser(user);
        return stuckTaskAlert;
    }

    private boolean matchesCreatorEmail(StuckTaskAlertFindCriteria criteria, StuckTaskAlert stuckTaskAlert) {
        return !criteria.hasCreatorEmail() || criteria.getCreatorEmail()
                .equals(stuckTaskAlert.getStuckTaskRule().getCreatedByUser().getEmail());
    }
}
