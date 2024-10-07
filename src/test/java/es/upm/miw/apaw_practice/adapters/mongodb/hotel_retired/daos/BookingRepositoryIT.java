package es.upm.miw.apaw_practice.adapters.mongodb.hotel_retired.daos;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class BookingRepositoryIT {

    @Autowired
    private BookingRepository bookingRepository;

    @Test
    void testCreateAndRead() {
        assertTrue(this.bookingRepository.findAll().stream()
                .anyMatch(bookingEntity ->
                        !bookingEntity.isConfirmed() &&
                        bookingEntity.getDateIn().equals(LocalDate.of(2024, 10, 1))
                ));
    }
}
