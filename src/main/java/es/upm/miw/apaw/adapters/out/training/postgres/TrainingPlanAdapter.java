package es.upm.miw.apaw.adapters.out.training.postgres;

import es.upm.miw.apaw.domain.model.training.TrainingPlan;
import es.upm.miw.apaw.domain.ports.out.training.TrainingPlanGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TrainingPlanAdapter implements TrainingPlanGateway {

    private final TrainingPlanRepository trainingPlanRepository;

    @Override
    public TrainingPlan create(TrainingPlan trainingPlan) {
        return this.trainingPlanRepository.save(new TrainingPlanEntity(trainingPlan)).toDomain();
    }
    
    @Override
    public boolean existsByPlanCode(String planCode) {
        return this.trainingPlanRepository.existsByPlanCode(planCode);
    }
}
