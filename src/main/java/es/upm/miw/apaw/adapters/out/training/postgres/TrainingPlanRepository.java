package es.upm.miw.apaw.adapters.out.training.postgres;

import org.springframework.data.repository.CrudRepository;
import java.util.UUID;

public interface TrainingPlanRepository extends CrudRepository<TrainingPlanEntity, UUID> {
}
