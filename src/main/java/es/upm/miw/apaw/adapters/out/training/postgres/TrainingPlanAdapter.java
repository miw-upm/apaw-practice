package es.upm.miw.apaw.adapters.out.training.postgres;

import es.upm.miw.apaw.domain.model.training.TrainingPlan;
import es.upm.miw.apaw.domain.ports.out.training.TrainingPlanGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TrainingPlanAdapter implements TrainingPlanGateway {

    private final TrainingPlanRepository trainingPlanRepository;
    private final CourseRepository courseRepository;

    @Override
    @Transactional
    public TrainingPlan create(TrainingPlan trainingPlan) {
        TrainingPlanEntity trainingPlanEntity = new TrainingPlanEntity(trainingPlan);
        List<CourseEntity> courseEntities = trainingPlan.getCourses().stream()
                .map(course -> this.courseRepository.getReferenceById(course.getId()))
                .collect(java.util.stream.Collectors.toCollection(ArrayList::new));
        trainingPlanEntity.setCourses(courseEntities);
        this.trainingPlanRepository.save(trainingPlanEntity);
        return trainingPlan;
    }
    
    @Override
    public boolean existsByPlanCode(String planCode) {
        return this.trainingPlanRepository.existsByPlanCode(planCode);
    }
}