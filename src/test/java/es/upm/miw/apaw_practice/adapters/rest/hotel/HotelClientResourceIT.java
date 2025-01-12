package es.upm.miw.apaw_practice.adapters.rest.hotel;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.hotel.HotelClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@RestTestConfig
public class HotelClientResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreate() {
        HotelClient client = new HotelClient("y9999999x", "David", "6000000000", "");
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
        HotelClient client = new HotelClient("y1111111x", "David", "6000000000", "");
        this.webTestClient
                .post()
                .uri(HotelClientResource.CLIENTS)
                .body(BodyInserters.fromValue(client))
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }

}
