package es.upm.miw.apaw.domain.services.stucktaskdetector;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.stucktaskdetector.StuckTaskAlert;
import es.upm.miw.apaw.domain.model.stucktaskdetector.StuckTaskAlertCreation;
import es.upm.miw.apaw.domain.model.stucktaskdetector.StuckTaskRule;
import es.upm.miw.apaw.domain.ports.out.stucktaskdetector.StuckTaskAlertGateway;
import es.upm.miw.apaw.domain.ports.out.stucktaskdetector.StuckTaskRuleGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StuckTaskAlertService {

    private final StuckTaskAlertGateway stuckTaskAlertGateway;
    private final StuckTaskRuleGateway stuckTaskRuleGateway;

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
}
