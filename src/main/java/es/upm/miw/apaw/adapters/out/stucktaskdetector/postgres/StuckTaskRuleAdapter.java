package es.upm.miw.apaw.adapters.out.stucktaskdetector.postgres;

import es.upm.miw.apaw.domain.model.stucktaskdetector.StuckTaskRule;
import es.upm.miw.apaw.domain.ports.out.stucktaskdetector.StuckTaskRuleGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class StuckTaskRuleAdapter implements StuckTaskRuleGateway {

    private final StuckTaskRuleRepository stuckTaskRuleRepository;

    @Override
    public Optional<StuckTaskRule> read(UUID id) {
        return this.stuckTaskRuleRepository.findById(id).map(StuckTaskRuleEntity::toDomain);
    }

    @Override
    public StuckTaskRule create(StuckTaskRule stuckTaskRule) {
        this.stuckTaskRuleRepository.save(new StuckTaskRuleEntity(stuckTaskRule));
        return stuckTaskRule;
    }

    @Override
    public boolean existsByName(String name) {
        return this.stuckTaskRuleRepository.existsByName(name);
    }
}