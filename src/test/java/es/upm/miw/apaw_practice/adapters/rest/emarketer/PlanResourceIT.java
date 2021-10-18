package es.upm.miw.apaw_practice.adapters.rest.emarketer;

import es.upm.miw.apaw_practice.adapters.mongodb.emarketer.daos.PlanRepository;
import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.emarketer.Plan;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertTrue;

@RestTestConfig
public class PlanResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private PlanRepository planRepository;

    @Test
    void testCreate() {
        Plan plan =
                new Plan("plan mes", new BigDecimal(55), 12);
        this.webTestClient
                .post()
                .uri(PlanResource.PLANS)
                .body(BodyInserters.fromValue(plan))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Plan.class)
                .value(Assertions::assertNotNull);

        assertTrue(this.planRepository.findAll().stream()
                .anyMatch(planBD ->
                        "plan mes".equals(planBD.getDescription()) &&
                                new BigDecimal(55).equals(planBD.getPrice()) &&
                                12 == planBD.getDuration()
                ));

    }
}
