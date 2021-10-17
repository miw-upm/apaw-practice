package es.upm.miw.apaw_practice.adapters.rest.emarketer;

import es.upm.miw.apaw_practice.domain.models.emarketer.Plan;
import es.upm.miw.apaw_practice.domain.services.emarketer.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(PlanResource.PLANS)
public class PlanResource {

    static final String PLANS = "/emarketer/plans";

    private final PlanService planService;

    @Autowired
    public PlanResource(PlanService planService) {
        this.planService = planService;
    }

    @PostMapping
    public Plan create(@RequestBody Plan plan) {
        return this.planService.create(plan);
    }

}
