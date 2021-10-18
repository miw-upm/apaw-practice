package es.upm.miw.apaw_practice.adapters.rest.hotel;

import es.upm.miw.apaw_practice.adapters.mongodb.hotel.daos.HotelGuestRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities.HotelGuestEntity;
import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.hotel.Hotel;
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

    @Autowired
    private HotelGuestRepository hotelGuestRepository;

    @BeforeEach
    void init() {
        LocalDateTime entryDate = LocalDateTime.of(2021, 9, 10, 16, 0);
        LocalDateTime departureDate = LocalDateTime.of(2021, 9, 25, 16, 0);

        HotelGuestEntity hotelGuestEntity = new HotelGuestEntity(HotelGuest.builder()
                .dni("11111111L")
                .name("Yaiza")
                .entryDate(entryDate)
                .departureDate(departureDate)
                .build());
        this.hotelGuestRepository.save(hotelGuestEntity);

    }

    @Test
    void testCreate() {
        LocalDateTime entryDate = LocalDateTime.of(2021, 9, 10, 16, 0);
        LocalDateTime departureDate = LocalDateTime.of(2021, 9, 25, 16, 0);

        HotelGuest hotelGuest = HotelGuest.builder()
                .dni("00000000R")
                .name("Juana")
                .entryDate(entryDate)
                .departureDate(departureDate)
                .build();

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

        HotelGuest hotelGuest = HotelGuest.builder()
                .dni("8384929P")
                .name(null)
                .entryDate(entryDate)
                .departureDate(departureDate)
                .build();

        Assertions.assertNull(hotelGuest.getName());
        Assertions.assertTrue(hotelGuest.isNull());
        this.webTestClient
                .post()
                .uri(HotelGuestResource.HOTELGUESTS)
                .body(BodyInserters.fromValue(hotelGuest))
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testReadByDni() {
        this.webTestClient
                .get()
                .uri(HotelGuestResource.HOTELGUESTS + HotelGuestResource.DNI, "11111111L")
                .exchange()
                .expectStatus().isOk()
                .expectBody(HotelGuest.class);
    }


    @Test
    void testDeleteByDni() {
        this.webTestClient
                .get()
                .uri(HotelGuestResource.HOTELGUESTS + HotelGuestResource.DNI, "11111111L")
                .exchange()
                .expectStatus().isOk()
                .expectBody(HotelGuest.class);

        this.webTestClient
                .delete()
                .uri(HotelGuestResource.HOTELGUESTS + HotelGuestResource.DNI, "11111111L")
                .exchange()
                .expectStatus().isOk();

        this.webTestClient
                .get()
                .uri(HotelGuestResource.HOTELGUESTS + HotelGuestResource.DNI, "11111111L")
                .exchange()
                .expectStatus().isNotFound();
    }



}
