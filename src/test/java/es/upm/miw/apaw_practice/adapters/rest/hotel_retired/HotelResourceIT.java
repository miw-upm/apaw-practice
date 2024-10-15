package es.upm.miw.apaw_practice.adapters.rest.hotel_retired;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.hotel_retired.Hotel;
import es.upm.miw.apaw_practice.domain.models.hotel_retired.Room;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static es.upm.miw.apaw_practice.adapters.rest.hotel_retired.HotelResource.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RestTestConfig
public class HotelResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreate() {
        Room[] rooms = {
                new Room("78678", false, 1, BigDecimal.valueOf(59.99), Collections.emptyList()),
                new Room("3789", false, 1, BigDecimal.valueOf(59.99), Collections.emptyList()),
        };
        Hotel hotel = new Hotel("D33069196", "Lacosta", "C/ Pedralves 32 Barcelona", Collections.emptyList());

        this.webTestClient
                .post()
                .uri(HOTELS)
                .body(BodyInserters.fromValue(hotel))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Hotel.class)
                .value(Assertions::assertNotNull);
    }

    @Test
    void testRead() {
        this.webTestClient
                .get()
                .uri(HOTELS + CIF_ID, "F91635847")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Hotel.class)
                .value(Assertions::assertNotNull)
                .value(hotel -> {
                    assertEquals("F91635847", hotel.getCif());
                    assertEquals("LaMaria", hotel.getHotelName());
                    assertEquals("C/ Mandrágora 32, Retuerta (Burgos)", hotel.getAddress());
                    assertEquals(19, hotel.getRooms().size());
                });
    }

    @Test
    void testReadNotFound() {
        this.webTestClient
                .get()
                .uri(HOTELS + CIF_ID, "FOO")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testDelete() {
        Room[] rooms = {
                new Room("34301", false, 1, BigDecimal.valueOf(59.99), Collections.emptyList()),
                new Room("2323", false, 1, BigDecimal.valueOf(59.99), Collections.emptyList()),
        };
        Hotel hotel = new Hotel("B28649549", "Lacosta", "C/ Pedralves 32 Barcelona", List.of(rooms));

        this.webTestClient
                .post()
                .uri(HOTELS)
                .body(BodyInserters.fromValue(hotel))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Hotel.class)
                .value(Assertions::assertNotNull);

        this.webTestClient
                .delete()
                .uri(HOTELS + CIF_ID, "B28649549")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testUpdate() {
        Room[] rooms = {
                new Room("65", false, 1, BigDecimal.valueOf(59.99), Collections.emptyList()),
                new Room("223", false, 1, BigDecimal.valueOf(59.99), Collections.emptyList()),
        };

        Hotel hotel = new Hotel("W2135920C", "Guacamayo", "C/ Pedralves 32 Barcelona", List.of(rooms));

        this.webTestClient
                .put()
                .uri(HOTELS + CIF_ID, "W2135920C")
                .body(BodyInserters.fromValue(hotel))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Hotel.class)
                .value(updatedHotel -> {
                    assertEquals("Guacamayo", updatedHotel.getHotelName());
                    assertEquals(List.of(rooms), updatedHotel.getRooms());
                });
    }

    @Test
    void testUpdateRooms() {
        Hotel hotel = new Hotel("E24206831", "Ventura", "C/ Pedralves 31 Barcelona", Collections.emptyList());

        this.webTestClient
                .post()
                .uri(HOTELS)
                .body(BodyInserters.fromValue(hotel))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Hotel.class)
                .value(Assertions::assertNotNull);

        List<Room> updatedRooms = List.of(
                new Room("967600", false, 1, BigDecimal.valueOf(59.99), Collections.emptyList()),
                new Room("67678", false, 1, BigDecimal.valueOf(59.99), Collections.emptyList())
        );

        this.webTestClient
                .patch()
                .uri(HOTELS + CIF_ID + ROOMS, "E24206831")
                .body(BodyInserters.fromValue(updatedRooms))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Hotel.class)
                .value(updatedHotel -> {
                    assertEquals(updatedRooms, updatedHotel.getRooms());
                });
    }

    @Test
    void testFindTotalSumOfPrice() {

        this.webTestClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder.path(HOTELS + SEARCH)
                                .queryParam("q", "hotelName: LaMaria;fullName: Emilio Pedrajas")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(BigDecimal.class)
                .value(value -> assertEquals(BigDecimal.valueOf(319.95), value));
    }
}
