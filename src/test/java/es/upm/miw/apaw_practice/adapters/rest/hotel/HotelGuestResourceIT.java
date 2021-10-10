package es.upm.miw.apaw_practice.adapters.rest.hotel;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.hotel.HotelGuest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RestTestConfig
class HotelGuestResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @BeforeEach
    void init(){

    }
    @Test
    void testCreate() {
        LocalDateTime entryDate = LocalDateTime.of(2021, 9, 10, 16, 0);
        LocalDateTime departureDate = LocalDateTime.of(2021, 9, 25, 16, 0);

        HotelGuest hotelGuest = new HotelGuest("Juana", "00000000R", entryDate, departureDate);
        this.webTestClient
                .post()
                .uri(HotelGuestResource.HOTELGUESTS)
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

        Assertions.assertNull(hotelGuest.getName());
        Assertions.assertTrue(hotelGuest.isNull());
        this.webTestClient
                .post()
                .uri(HotelGuestResource.HOTELGUESTS)
                .body(BodyInserters.fromValue(hotelGuest))
                .exchange()
                .expectStatus().isBadRequest();
    }

   //TODO -> COMPROBAR QUE FUNCIONA
    @Test
    void testDeleteByDni(){
        HotelGuest hotelGuest = new HotelGuest("Mario", "88888888K", null, null);
        this.webTestClient
                .delete()
                .uri(HotelGuestResource.HOTELGUESTS + HotelGuestResource.DNI , "88888888K")
                .exchange()
                .expectStatus().isOk();
    }

}
