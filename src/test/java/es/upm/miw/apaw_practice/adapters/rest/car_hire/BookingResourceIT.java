package es.upm.miw.apaw_practice.adapters.rest.car_hire;

import es.upm.miw.apaw_practice.adapters.mongodb.car_hire.daos.BookingRepository;
import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RestTestConfig
public class BookingResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    BookingRepository bookingRepository;

    @Test
    void testDeleteByBookingNumber() {
        String bookingNumber = "1403";

        assertTrue(this.bookingRepository.findAll().stream()
                .anyMatch(bookingEntity ->
                        bookingEntity.getBookingNumber().equals(bookingNumber)));

        this.webTestClient
                .delete()
                .uri(BookingResource.BOOKING + BookingResource.BOOKING_NUMBER, bookingNumber)
                .exchange()
                .expectStatus().isOk();

        assertFalse(this.bookingRepository.findAll().stream()
                .anyMatch(bookingEntity ->
                        bookingEntity.getBookingNumber().equals(bookingNumber)));
    }
}
