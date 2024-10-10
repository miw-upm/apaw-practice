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

import static es.upm.miw.apaw_practice.adapters.rest.hotel_retired.HotelResource.CIF_ID;
import static es.upm.miw.apaw_practice.adapters.rest.hotel_retired.HotelResource.HOTELS;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RestTestConfig
public class HotelResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreate() {
        Room[] rooms = {
                new Room("34301", false, 1, BigDecimal.valueOf(59.99), Collections.emptyList()),
                new Room("2323", false, 1, BigDecimal.valueOf(59.99), Collections.emptyList()),
        };
        Hotel hotel = new Hotel("D33069196", "Lacosta", "C/ Pedralves 32 Barcelona", List.of(rooms));

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
}
