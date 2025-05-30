package es.upm.miw.apaw_practice.adapters.rest.hotel;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.hotel.HotelMain;
import es.upm.miw.apaw_practice.domain.models.hotel.HotelRoom;
import es.upm.miw.apaw_practice.domain.models.shop.Tag;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static es.upm.miw.apaw_practice.adapters.rest.hotel.HotelMainResource.*;
import static org.junit.jupiter.api.Assertions.*;

@RestTestConfig
public class HotelMainResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testFindByName() {
        this.webTestClient
                .get()
                .uri(HOTELS + NAMES, "xiangHotel")
                .exchange()
                .expectStatus().isOk()
                .expectBody(HotelMain.class)
                .value(Assertions::assertNotNull)
                .value(hotel -> {
                    assertEquals("xiangHotel",hotel.getName());
                    assertEquals("966666666",hotel.getPhone());
                });
    }

    @Test
    void testDelete() {
        this.webTestClient
                .delete()
                .uri(HOTELS + NAMES, "mengfeiHotel")
                .exchange()
                .expectStatus().isOk();
    }
    @Test
    void testUpdateRoom() {
        HotelRoom room = new HotelRoom("101", "doble", new BigDecimal("40.00"), false);
        HotelMain updatedHotel = this.webTestClient
                .put()
                .uri(HOTELS + NAMES + ROOMS + NUMBERS, "mengfeiHotel", "101")
                .body(BodyInserters.fromValue(room))
                .exchange()
                .expectStatus().isOk()
                .expectBody(HotelMain.class)
                .returnResult()
                .getResponseBody();
        HotelRoom roomUpdated = updatedHotel.getRooms().stream()
                .filter(r -> r.getNumber().equals("101"))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Room not found: " + "101"));

        assertEquals("doble", roomUpdated.getType());
        assertEquals(new BigDecimal("40.00"), room.getPrice());
        assertFalse(roomUpdated.isReserved());
    }
    @Test
    void testUpdateRoomNotFound() {
        HotelRoom room = new HotelRoom("120", "doble", new BigDecimal("40.00"), false);
        this.webTestClient
                .put()
                .uri(HOTELS + NAMES + ROOMS + NUMBERS, "mengfeiHotel", "101")
                .body(BodyInserters.fromValue(room))
                .exchange()
                .expectStatus().isNotFound();
    }

}
