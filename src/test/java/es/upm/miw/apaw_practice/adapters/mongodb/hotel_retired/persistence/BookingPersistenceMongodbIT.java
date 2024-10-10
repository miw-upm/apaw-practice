package es.upm.miw.apaw_practice.adapters.mongodb.hotel_retired.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.hotel_retired.Booking;
import es.upm.miw.apaw_practice.domain.models.hotel_retired.Guest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class BookingPersistenceMongodbIT {

    @Autowired
    private BookingPersistenceMongodb bookingPersistenceMongodb;

    @Autowired
    private GuestPersistenceMongodb guestPersistenceMongodb;

    @Test
    void testCreateAndRead() {
        Guest guest = this.guestPersistenceMongodb.read("99527370E");
        Booking booking = new Booking(
                false,
                LocalDate.of(2024, 10, 1),
                LocalDate.of(2024, 10, 1),
                guest
        );
        Booking createdBooking = this.bookingPersistenceMongodb
                .create(booking);
        assertFalse(createdBooking.getConfirmed());
        assertEquals(LocalDate.of(2024, 10, 1), createdBooking.getDateIn());
        assertEquals(LocalDate.of(2024, 10, 1), createdBooking.getDateOut());
        assertEquals(guest, createdBooking.getGuest());
    }
}
