package es.upm.miw.apaw_practice.domain.services.emarketer;

import es.upm.miw.apaw_practice.domain.models.emarketer.Plan;
import es.upm.miw.apaw_practice.domain.persistence_ports.emarketer.PlanPersistence;
import org.springframework.stereotype.Service;

@Service
public class PlanService {

    private final PlanPersistence planPersistence;

    public PlanService(PlanPersistence planPersistence) {
        this.planPersistence = planPersistence;
    }

    public Plan create(Plan plan) {
        return this.planPersistence.create(plan);
    }
}
