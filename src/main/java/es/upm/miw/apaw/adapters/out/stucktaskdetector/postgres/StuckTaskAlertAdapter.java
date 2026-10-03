package es.upm.miw.apaw.adapters.out.stucktaskdetector.postgres;

import es.upm.miw.apaw.domain.ports.out.stucktaskdetector.StuckTaskAlertGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StuckTaskAlertAdapter implements StuckTaskAlertGateway {
    private final StuckTaskAlertRepository stuckTaskAlertRepository;
}
