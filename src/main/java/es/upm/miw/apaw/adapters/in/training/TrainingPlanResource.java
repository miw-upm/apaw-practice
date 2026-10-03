package es.upm.miw.apaw.adapters.in.training;

import es.upm.miw.apaw.domain.model.training.TrainingPlan;
import es.upm.miw.apaw.domain.model.training.CreationTrainingPlan;
import es.upm.miw.apaw.domain.services.training.TrainingPlanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(TrainingPlanResource.TRAINING_PLANS)
@RequiredArgsConstructor
public class TrainingPlanResource {

    public static final String TRAINING_PLANS = "/training/training-plans";

    private final TrainingPlanService trainingPlanService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TrainingPlan create(@Valid @RequestBody CreationTrainingPlan creation) {
        return this.trainingPlanService.create(creation);
    
    @GetMapping
    public List<TrainingPlan> find(@RequestBody es.upm.miw.apaw.domain.model.training.TrainingPlanFindCriteria criteria) {
        return this.trainingPlanService.find(criteria);
    }
}
