package es.upm.miw.apaw_practice.adapters.rest.hotel;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.adapters.rest.shop.ArticleResource;
import es.upm.miw.apaw_practice.domain.models.hotel.HotelGuest;
import es.upm.miw.apaw_practice.domain.models.shop.Article;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RestTestConfig
public class HotelGuestResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreate(){
        LocalDateTime entryDate = LocalDateTime.of(2021, 9, 10, 16, 0);
        LocalDateTime departureDate = LocalDateTime.of(2021, 9, 25, 16, 0);

        HotelGuest hotelGuest = new HotelGuest("Juana", "00000000R", entryDate, departureDate);
        this.webTestClient
                .post()
                .uri(HotelGuestResource.HOTELGUEST)
                .body(BodyInserters.fromValue(hotelGuest))
                .exchange()
                .expectStatus().isOk()
                .expectBody(HotelGuest.class)
                .value(Assertions::assertNotNull);
    }

    @Test
    void testCreateBadRequest() {
        LocalDateTime entryDate = LocalDateTime.of(2021, 9, 10, 16, 0);
        LocalDateTime departureDate = LocalDateTime.of(2021, 9, 25, 16, 0);

        HotelGuest hotelGuest = new HotelGuest(null, "8384929P", entryDate, departureDate);

        Assertions.assertNull(hotelGuest.getNameGuest());
        Assertions.assertTrue(hotelGuest.isNull());
        this.webTestClient
                .post()
                .uri(HotelGuestResource.HOTELGUEST)
                .body(BodyInserters.fromValue(hotelGuest))
                .exchange()
                .expectStatus().isBadRequest();
    }

}
