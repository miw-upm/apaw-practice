package es.upm.miw.apaw_practice.adapters.mongodb.car_hire.daos;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
class BookingRepositoryIT {

    @Autowired
    private BookingRepository bookingRepository;

    @Test
    void testCreateAndRead() {
        assertTrue(bookingRepository.findAll().stream()
                .anyMatch(booking ->
                        "1403".equals(booking.getBookingNumber()) &&
                                12 == booking.getNumberDays() &&
                                booking.getId() != null &&
                                booking.getHiredDate().isBefore(LocalDateTime.now()) &&
                                1 == booking.getVehicleEntities().size() &&
                                booking.getVehicleEntities().get(0).getVinNumber().equals("VSSZZZ6KZ1R149943") &&
                                booking.getRenterEntity().getName().equals("Pablo")
                ));
    }

}
