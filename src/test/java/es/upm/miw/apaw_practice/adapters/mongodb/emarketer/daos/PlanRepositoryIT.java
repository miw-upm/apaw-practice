package es.upm.miw.apaw_practice.adapters.mongodb.emarketer.daos;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class PlanRepositoryIT {

    @Autowired
    private PlanRepository planRepository;

    @Test
    void testCreateAndRead() {
        assertTrue(this.planRepository.findAll().stream()
                .anyMatch(plan ->
                        "plan dia".equals(plan.getDescription()) &&
                                12 == plan.getDuration() &&
                                new BigDecimal("55").equals(plan.getPrice()) &&
                                        plan.getId() != null
                ));
    }

}
