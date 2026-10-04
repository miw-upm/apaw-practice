package es.upm.miw.apaw.domain.ports.out.stucktaskdetector;

import es.upm.miw.apaw.domain.model.stucktaskdetector.StuckTaskAlert;

public interface StuckTaskAlertGateway {
    StuckTaskAlert create(StuckTaskAlert stuckTaskAlert);

    boolean existsByReference(String reference);
}
