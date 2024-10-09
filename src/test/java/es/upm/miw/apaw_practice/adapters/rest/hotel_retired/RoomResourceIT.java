package es.upm.miw.apaw_practice.adapters.rest.hotel_retired;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.hotel_retired.Room;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.math.BigDecimal;
import java.util.Collections;

@RestTestConfig
public class RoomResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreate() {
        Room room = new Room("56767", false, 2, BigDecimal.valueOf(59.99), Collections.emptyList());
        this.webTestClient
                .post()
                .uri(RoomResource.ROOMS)
                .body(BodyInserters.fromValue(room))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Room.class)
                .value(Assertions::assertNotNull);
    }
}
