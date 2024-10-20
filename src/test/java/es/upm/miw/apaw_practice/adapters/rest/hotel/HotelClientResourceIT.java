package es.upm.miw.apaw_practice.adapters.rest.hotel;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.hotel.HotelClient;
import es.upm.miw.apaw_practice.domain.models.hotel.HotelReservation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.time.LocalDate;

@RestTestConfig
public class HotelClientResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreate() {
        HotelReservation reservation = new HotelReservation("4", "202", LocalDate.of(2020, 1, 5));
        HotelClient client = new HotelClient("x6666666x", "Mengtxu", "677777777", "Mengtxu@gmail.com", reservation);
        this.webTestClient
                .post()
                .uri(HotelClientResource.CLIENTS)
                .body(BodyInserters.fromValue(client))
                .exchange()
                .expectStatus().isOk()
                .expectBody(HotelClient.class)
                .value(Assertions::assertNotNull);
    }

    @Test
    void testCreateConflictDNI() {
        HotelReservation reservation = new HotelReservation("5", "202", LocalDate.of(2020, 1, 5));
        HotelClient client = new HotelClient("y1111111x", "David", "6000000000", "", reservation);
        this.webTestClient
                .post()
                .uri(HotelClientResource.CLIENTS)
                .body(BodyInserters.fromValue(client))
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void testCreateConflictReservation() {
        HotelReservation reservation = new HotelReservation("1", "101", LocalDate.of(2020,1,1));
        HotelClient client = new HotelClient("y9999999x", "David", "6000000000", "", reservation);
        this.webTestClient
                .post()
                .uri(HotelClientResource.CLIENTS)
                .body(BodyInserters.fromValue(client))
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }

}
