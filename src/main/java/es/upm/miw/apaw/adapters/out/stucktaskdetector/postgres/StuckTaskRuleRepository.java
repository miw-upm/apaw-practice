package es.upm.miw.apaw.adapters.out.stucktaskdetector.postgres;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StuckTaskRuleRepository extends JpaRepository<StuckTaskRuleEntity, UUID> {
}
