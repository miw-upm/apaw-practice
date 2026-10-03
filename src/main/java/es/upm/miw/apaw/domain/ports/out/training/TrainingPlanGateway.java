package es.upm.miw.apaw.domain.ports.out.training;

import es.upm.miw.apaw.domain.model.training.TrainingPlan;

public interface TrainingPlanGateway {
    TrainingPlan create(TrainingPlan trainingPlan);
    boolean existsByPlanCode(String planCode);
    TrainingPlan find(es.upm.miw.apaw.domain.model.training.TrainingPlanFindCriteria criteria);
}
