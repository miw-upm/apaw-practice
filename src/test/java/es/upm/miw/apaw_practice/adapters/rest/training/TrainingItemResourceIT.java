package es.upm.miw.apaw_practice.adapters.rest.training;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import static es.upm.miw.apaw_practice.adapters.rest.training.TrainingItemResource.TRAININGITEMS;
import static es.upm.miw.apaw_practice.adapters.rest.training.TrainingItemResource.NAME;

@RestTestConfig
public class TrainingItemResourceIT {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testDelete() {
        this.webTestClient
                .delete()
                .uri(TRAININGITEMS + NAME, "kk")
                .exchange()
                .expectStatus().isOk();
    }
}
