package es.upm.miw.apaw_practice.adapters.rest.hotel;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.hotel.HotelMain;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import static es.upm.miw.apaw_practice.adapters.rest.hotel.HotelMainResource.HOTELS;
import static es.upm.miw.apaw_practice.adapters.rest.hotel.HotelMainResource.NAMES;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

}
