package es.upm.miw.apaw.domain.ports.out.stucktaskdetector;

import es.upm.miw.apaw.domain.model.stucktaskdetector.StuckTaskRule;

import java.util.Optional;
import java.util.UUID;

public interface StuckTaskRuleGateway {
    Optional<StuckTaskRule> read(UUID id);

    StuckTaskRule create(StuckTaskRule stuckTaskRule);

    boolean existsByName(String name);
}
