package es.upm.miw.apaw_practice.adapters.rest.gym;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.gym.Gym;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RestTestConfig
public class GymResourceIT {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testFindByLabel() {
        this.webTestClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder.path(GymResource.Gyms + GymResource.Search)
                                .queryParam("label", "Basic Fit")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Gym.class)
                .value(gyms -> assertEquals("calle Gran via 82", gyms.get(0).getAddress()));

    }
}
