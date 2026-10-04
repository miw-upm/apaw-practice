package es.upm.miw.apaw.adapters.out.stucktaskdetector.postgres;
import es.upm.miw.apaw.domain.model.stucktaskdetector.StuckTaskAlert;
import es.upm.miw.apaw.domain.ports.out.stucktaskdetector.StuckTaskAlertGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class StuckTaskAlertAdapter implements StuckTaskAlertGateway {

    private final StuckTaskAlertRepository stuckTaskAlertRepository;
    private final StuckTaskRuleRepository stuckTaskRuleRepository;

    @Override
    @Transactional
    public StuckTaskAlert create(StuckTaskAlert stuckTaskAlert) {
        StuckTaskAlertEntity entity = new StuckTaskAlertEntity(stuckTaskAlert);
        entity.setStuckTaskRule(this.stuckTaskRuleRepository.getReferenceById(stuckTaskAlert.getStuckTaskRule().getId()));
        this.stuckTaskAlertRepository.save(entity);
        return stuckTaskAlert;
    }

    @Override
    public boolean existsByReference(String reference) {
        return this.stuckTaskAlertRepository.existsByReference(reference);
    }
}
