package es.upm.miw.apaw_practice.adapters.rest.car_hire;

import es.upm.miw.apaw_practice.adapters.mongodb.car_hire.daos.RenterRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.car_hire.entities.RenterEntity;
import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.car_hire.Renter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static org.junit.jupiter.api.Assertions.*;

@RestTestConfig
public class RenterResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    RenterRepository renterRepository;

    @Test
    void testCreate() {
        assertFalse(this.renterRepository.findByDni("12345678A").isPresent());

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

        assertTrue(this.renterRepository.findByDni("12345678A").isPresent());
        RenterEntity createdRenter = this.renterRepository.findByDni("12345678A").get();
        assertEquals("Pepito", createdRenter.getName());
        assertNull(createdRenter.getLikedCar());
    }
}
