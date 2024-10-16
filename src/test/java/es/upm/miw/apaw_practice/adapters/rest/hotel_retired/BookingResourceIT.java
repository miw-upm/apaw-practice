package es.upm.miw.apaw_practice.adapters.rest.hotel_retired;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.hotel_retired.Booking;
import es.upm.miw.apaw_practice.domain.models.hotel_retired.Guest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static es.upm.miw.apaw_practice.adapters.rest.hotel_retired.BookingResource.BOOKINGS;

@RestTestConfig
public class BookingResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreate() {
        Guest guest = Guest.builder()
                .nif("99527370E")
                .fullName("Emilio Pedrajas")
                .birthDay(LocalDateTime.of(1990, 10, 27,  23, 2, 2 ))
                .build();
        Booking booking = new Booking(false, LocalDate.now(), LocalDate.now().plusWeeks(1), guest);
        this.webTestClient
                .post()
                .uri(BOOKINGS)
                .body(BodyInserters.fromValue(booking))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Booking.class)
                .value(Assertions::assertNotNull);
    }
}
