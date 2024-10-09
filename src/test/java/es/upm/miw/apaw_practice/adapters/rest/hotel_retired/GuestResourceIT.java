package es.upm.miw.apaw_practice.adapters.rest.hotel_retired;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.hotel_retired.Guest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.time.LocalDateTime;

import static es.upm.miw.apaw_practice.adapters.rest.hotel_retired.GuestResource.GUESTS;
import static es.upm.miw.apaw_practice.adapters.rest.hotel_retired.GuestResource.NIF_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RestTestConfig
public class GuestResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreate() {
        Guest guest = new Guest(
                "51408710F",
                "Pedro Nieto",
                LocalDateTime.of(1990, 10, 27, 23, 2, 2)
        );
        this.webTestClient
                .post()
                .uri(GUESTS)
                .body(BodyInserters.fromValue(guest))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Guest.class)
                .value(Assertions::assertNotNull);
    }

    @Test
    void testRead() {
        this.webTestClient
                .get()
                .uri(GUESTS + NIF_ID, "99527370E")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Guest.class)
                .value(Assertions::assertNotNull)
                .value(guest -> {
                    assertEquals("99527370E", guest.getNif());
                    assertEquals("Emilio Pedrajas", guest.getFullName());
                    assertEquals(LocalDateTime.of(1990, 10, 27,  23, 2, 2 ), guest.getBirthDay());
                });
    }

    @Test
    void testReadNotFound() {
        this.webTestClient
                .get()
                .uri(GUESTS + NIF_ID, "FOO")
                .exchange()
                .expectStatus().isNotFound();
    }
}
