package es.upm.miw.apaw.adapters.out.training.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TrainingPlanRepository extends JpaRepository<TrainingPlanEntity, UUID>, JpaSpecificationExecutor<TrainingPlanEntity> {
    boolean existsByPlanCode(String planCode);
    boolean existsByCoursesId(UUID id);

    @org.springframework.data.jpa.repository.Query("""
            select new es.upm.miw.apaw.domain.model.training.TrainingModalityReport(
                course.online,
                count(distinct plan),
                sum(course.durationHours)
            )
            from TrainingPlanEntity plan
            join plan.courses course
            group by course.online
            """)
    java.util.List<es.upm.miw.apaw.domain.model.training.TrainingModalityReport> findTrainingModalityReport();
}