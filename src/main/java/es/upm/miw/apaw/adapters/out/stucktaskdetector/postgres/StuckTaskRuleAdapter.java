package es.upm.miw.apaw.adapters.out.stucktaskdetector.postgres;

import es.upm.miw.apaw.domain.ports.out.stucktaskdetector.StuckTaskRuleGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StuckTaskRuleAdapter implements StuckTaskRuleGateway {
    private final StuckTaskRuleRepository stuckTaskRuleRepository;
}