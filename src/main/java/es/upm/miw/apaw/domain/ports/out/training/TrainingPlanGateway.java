package es.upm.miw.apaw.domain.ports.out.training;

import es.upm.miw.apaw.domain.model.training.TrainingPlan;
import es.upm.miw.apaw.domain.model.training.TrainingPlanFindCriteria;
import java.util.List;

public interface TrainingPlanGateway {
    TrainingPlan create(TrainingPlan trainingPlan);
    boolean existsByPlanCode(String planCode);
    List<TrainingPlan> find(TrainingPlanFindCriteria criteria);
}
