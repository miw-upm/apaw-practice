package es.upm.miw.apaw_practice.adapters.mongodb.emarketer.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.emarketer.daos.PlanRepository;
import es.upm.miw.apaw_practice.domain.models.emarketer.Plan;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class PlanPersistenceMongodbIT {

    @Autowired
    private PlanPersistenceMongodb planPersistenceMongodb;

    @Autowired
    private PlanRepository planRepository;

    @Test
    void testCreateAndRead() {
        Plan plan = new Plan("plan dias alternos", new BigDecimal(45), 24);
        this.planPersistenceMongodb.create(plan);
        assertTrue(this.planRepository.findAll().stream()
                .anyMatch(planBD ->
                        "plan dias alternos".equals(planBD.getDescription()) &&
                                new BigDecimal(45).equals(planBD.getPrice()) &&
                                24 == planBD.getDuration()
                ));
    }
}

