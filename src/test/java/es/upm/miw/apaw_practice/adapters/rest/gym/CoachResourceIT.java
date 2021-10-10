package es.upm.miw.apaw_practice.adapters.rest.gym;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import static es.upm.miw.apaw_practice.adapters.rest.gym.CoachResource.DNI;
import static es.upm.miw.apaw_practice.adapters.rest.gym.CoachResource.gyms;

@RestTestConfig
public class CoachResourceIT {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testDelete() {
        this.webTestClient
                .delete()
                .uri(gyms + DNI, "2356892A")
                .exchange()
                .expectStatus().isOk();
    }
}
