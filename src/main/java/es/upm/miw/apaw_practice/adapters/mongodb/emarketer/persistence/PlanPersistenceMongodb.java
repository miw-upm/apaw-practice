package es.upm.miw.apaw_practice.adapters.mongodb.emarketer.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.emarketer.daos.PlanRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.emarketer.entities.PlanEntity;
import es.upm.miw.apaw_practice.domain.models.emarketer.Plan;
import es.upm.miw.apaw_practice.domain.persistence_ports.emarketer.PlanPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("PlanPersistence")
public class PlanPersistenceMongodb implements PlanPersistence {

    private final PlanRepository planRepository;

    @Autowired
    public PlanPersistenceMongodb(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    @Override
    public Plan create(Plan plan) {
        return this.planRepository
                .save(new PlanEntity(plan))
                .toPlan();
    }
}
