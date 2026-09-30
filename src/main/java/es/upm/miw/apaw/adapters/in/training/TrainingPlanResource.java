package es.upm.miw.apaw.adapters.in.training;

import es.upm.miw.apaw.domain.model.training.TrainingPlan;
import es.upm.miw.apaw.domain.model.training.CreationTrainingPlan;
import es.upm.miw.apaw.domain.services.training.TrainingPlanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(TrainingPlanResource.TRAINING_PLANS)
@RequiredArgsConstructor
public class TrainingPlanResource {

    public static final String TRAINING_PLANS = "/training/training-plans";

    private final TrainingPlanService trainingPlanService;

    @PostMapping
    public TrainingPlan create(@Valid @RequestBody CreationTrainingPlan creation) {
        return this.trainingPlanService.create(creation);
    }
}
