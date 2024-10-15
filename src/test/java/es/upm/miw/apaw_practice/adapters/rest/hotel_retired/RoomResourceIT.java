package es.upm.miw.apaw_practice.adapters.rest.hotel_retired;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.hotel_retired.Booking;
import es.upm.miw.apaw_practice.domain.models.hotel_retired.Guest;
import es.upm.miw.apaw_practice.domain.models.hotel_retired.Room;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static es.upm.miw.apaw_practice.adapters.rest.hotel_retired.RoomResource.*;
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

    @Test
    void testUpdate() {
        Room room = new Room("286", false, 4, BigDecimal.valueOf(99.99), Collections.emptyList());
        this.webTestClient
                .post()
                .uri(ROOMS)
                .body(BodyInserters.fromValue(room))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Room.class)
                .value(Assertions::assertNotNull);

        room.setNumBeds(2);
        room.setPrice(BigDecimal.valueOf(59.99));

        this.webTestClient
                .put()
                .uri(ROOMS + NUM_ID, "286")
                .body(BodyInserters.fromValue(room))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Room.class)
                .value(updatedRoom -> {
                    assertEquals(2, updatedRoom.getNumBeds());
                    assertEquals(BigDecimal.valueOf(59.99), updatedRoom.getPrice());
                });
    }

    @Test
    void testUpdateBookings() {
        Room room = new Room("56578", false, 4, BigDecimal.valueOf(99.99), Collections.emptyList());
        this.webTestClient
                .post()
                .uri(ROOMS)
                .body(BodyInserters.fromValue(room))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Room.class)
                .value(Assertions::assertNotNull);

        Guest guest = new Guest(
                "03948142P",
                "Carla Sempere",
                LocalDateTime.of(2021, 4, 12, 23, 2, 2)
        );
        List<Booking> updatedBookings = List.of(
                new Booking(false, LocalDate.now(), LocalDate.now().plusWeeks(1), guest)
        );

         this.webTestClient
                .patch()
                .uri(ROOMS + NUM_ID + BOOKINGS, "56578")
                .body(BodyInserters.fromValue(updatedBookings))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Room.class)
                .value(updatedRoom -> {
                    assertEquals(1, updatedRoom.getBookings().size());
                });
    }
}
