package es.upm.miw.apaw.domain.ports.out.stucktaskdetector;

import es.upm.miw.apaw.domain.model.stucktaskdetector.StuckTaskAlert;
import es.upm.miw.apaw.domain.model.stucktaskdetector.StuckTaskRuleAlertReport;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StuckTaskAlertGateway {
    StuckTaskAlert create(StuckTaskAlert stuckTaskAlert);

    boolean existsByReference(String reference);

    Optional<StuckTaskAlert> read(UUID id);

    StuckTaskAlert update(StuckTaskAlert stuckTaskAlert);

    void delete(UUID id);

    List<StuckTaskAlert> findAll();
}
