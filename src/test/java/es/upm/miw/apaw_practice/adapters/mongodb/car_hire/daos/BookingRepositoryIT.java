package es.upm.miw.apaw_practice.adapters.mongodb.car_hire.daos;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class BookingRepositoryIT {

    @Autowired
    private BookingRepository bookingRepository;

    @Test
    void testCreateAndRead() {
        assertTrue(this.bookingRepository.findAll().stream()
                .anyMatch(bookingEntity ->
                        "1403".equals(bookingEntity.getBookingNumber()) &&
                                12 == bookingEntity.getNumberDays() &&
                                bookingEntity.getId() != null &&
                                bookingEntity.getHiredDate().isBefore(LocalDateTime.now()) &&
                                1 == bookingEntity.getVehicleEntities().size() &&
                                bookingEntity.getVehicleEntities().get(0).getVinNumber().equals("VSSZZZ6KZ1R149943") &&
                                bookingEntity.getRenterEntity().getName().equals("Pablo")
                )
        );
    }

    @Test
    void testDeleteByBookingNumber() {
        assertEquals("1403", this.bookingRepository.findAll().get(0).getBookingNumber());

        this.bookingRepository.deleteByBookingNumber("1403");

        assertFalse(this.bookingRepository.findAll().stream()
                .anyMatch(bookingEntity -> bookingEntity.getBookingNumber().equals("1403"))
        );
    }
}
