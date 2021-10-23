package es.upm.miw.apaw_practice.adapters.rest.car_hire;

import es.upm.miw.apaw_practice.adapters.mongodb.car_hire.daos.RenterRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.car_hire.entities.RenterEntity;
import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.car_hire.Renter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static org.junit.jupiter.api.Assertions.*;

@RestTestConfig
class RenterResourceIT {

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

    @Test
    void testCreateConflict() {
        Renter renter = new Renter("Repeated", "51435421N");
        Assertions.assertNull(renter.getLikedCar());
        this.webTestClient
                .post()
                .uri(RenterResource.RENTER)
                .body(BodyInserters.fromValue(renter))
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void testUpdateLikedCar() {
        assertTrue(this.renterRepository.findByDni("51435421N").isPresent());
        RenterEntity renterEntityToUpdate = this.renterRepository.findByDni("51435421N").get();
        assertEquals("Pablo", renterEntityToUpdate.getName());
        assertNull(renterEntityToUpdate.getLikedCar());

        renterEntityToUpdate.setLikedCar(true);
        Renter renterToUpdate = renterEntityToUpdate.toRenter();
        this.webTestClient
                .patch()
                .uri(RenterResource.RENTER + RenterResource.DNI, renterToUpdate.getDni())
                .body(BodyInserters.fromValue(renterEntityToUpdate))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Renter.class)
                .value(Assertions::assertNotNull)
                .value(renter -> assertTrue(renter.getLikedCar()));

        RenterEntity renterEntityUpdated = this.renterRepository.findByDni("51435421N").get();
        assertTrue(renterEntityUpdated.getLikedCar());
    }

    @Test
    void testUpdateLikedCarNotFound() {
        Renter renterInvented = new Renter("name", "000");
        this.webTestClient
                .patch()
                .uri(RenterResource.RENTER + RenterResource.DNI, renterInvented.getDni())
                .body(BodyInserters.fromValue(renterInvented))
                .exchange()
                .expectStatus().isNotFound();
    }
}
