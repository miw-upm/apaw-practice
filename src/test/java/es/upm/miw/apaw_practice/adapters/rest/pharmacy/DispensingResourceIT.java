package es.upm.miw.apaw_practice.adapters.rest.pharmacy;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.pharmacy.Dispensing;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@RestTestConfig
public class DispensingResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testUpdateDispensing() {
        this.webTestClient
                .put()
                .uri(DispensingResource.DISPENSINGS + DispensingResource.ID_ID, "kk")
                .body(BodyInserters.fromValue(new Dispensing()))
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testDeleteDispensing() {
        this.webTestClient
                .delete()
                .uri(DispensingResource.DISPENSINGS + DispensingResource.ID_ID, "kk")
                .exchange()
                .expectStatus().isOk();
    }
}
