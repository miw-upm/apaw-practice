package es.upm.miw.apaw.adapters.out.training.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface TrainingPlanRepository extends JpaRepository<TrainingPlanEntity, UUID> {
    boolean existsByPlanCode(String planCode);
    boolean existsByCoursesId(UUID id);
}
