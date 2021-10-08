package es.upm.miw.apaw_practice.adapters.rest.car_hire;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.car_hire.Renter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@RestTestConfig
public class RenterResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreate() {
        Renter renter = new Renter("Pepito", "12345678A");
        Assertions.assertNull(renter.getLikedCar());
        this.webTestClient
                .post()
                .uri(RenterResource.RENTER)
                .body(BodyInserters.fromValue(renter))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Renter.class)
                .value(Assertions::assertNotNull);
    }
}
