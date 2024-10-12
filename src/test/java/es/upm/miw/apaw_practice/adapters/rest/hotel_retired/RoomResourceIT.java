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

import static es.upm.miw.apaw_practice.adapters.rest.hotel_retired.RoomResource.NUM_ID;
import static es.upm.miw.apaw_practice.adapters.rest.hotel_retired.RoomResource.ROOMS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@RestTestConfig
public class RoomResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreate() {
        Room room = new Room("56767", false, 2, BigDecimal.valueOf(59.99), Collections.emptyList());
        this.webTestClient
                .post()
                .uri(ROOMS)
                .body(BodyInserters.fromValue(room))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Room.class)
                .value(Assertions::assertNotNull);
    }

    @Test
    void testRead() {
        this.webTestClient
                .get()
                .uri(ROOMS + NUM_ID, "101")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Room.class)
                .value(Assertions::assertNotNull)
                .value(room -> {
                    assertEquals("101", room.getNum());
                    assertEquals(BigDecimal.valueOf(59.99), room.getPrice());
                    assertEquals(1, room.getNumBeds());
                    assertFalse(room.getOccupied());
                    assertEquals(7, room.getBookings().size());
                });
    }

    @Test
    void testReadNotFound() {
        this.webTestClient
                .get()
                .uri(ROOMS + NUM_ID, "O")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testDelete() {
        Room room = new Room("4677", false, 4, BigDecimal.valueOf(99.99), Collections.emptyList());
        this.webTestClient
                .post()
                .uri(ROOMS)
                .body(BodyInserters.fromValue(room))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Room.class)
                .value(Assertions::assertNotNull);

        this.webTestClient
                .delete()
                .uri(ROOMS + NUM_ID, "4677")
                .exchange()
                .expectStatus().isOk();
    }
}
