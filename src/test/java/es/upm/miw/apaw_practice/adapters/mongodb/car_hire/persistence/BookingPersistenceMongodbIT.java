package es.upm.miw.apaw_practice.adapters.mongodb.car_hire.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.car_hire.CarHireSeederService;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.car_hire.Booking;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class BookingPersistenceMongodbIT {

    @Autowired
    BookingPersistenceMongodb bookingPersistenceMongodb;

    @Autowired
    CarHireSeederService carHireSeederService;

    @BeforeEach
    void seedDatabase() {
        this.carHireSeederService.deleteAll();
        this.carHireSeederService.seedDatabase();
    }

    @Test
    void testReadByRenterNameNotFound() {
        assertThrows(NotFoundException.class, () -> this.bookingPersistenceMongodb.readByRenterName("invented"));
    }

    @Test
    void testReadByRenterName() {
        List<Booking> booking = this.bookingPersistenceMongodb.readByRenterName("Pablo").collect(Collectors.toList());

        assertEquals("1403", booking.get(0).getBookingNumber());
        assertEquals(12, booking.get(0).getNumberDays());

        assertEquals("Pablo", booking.get(0).getRenter().getName());
        assertEquals("51435421N", booking.get(0).getRenter().getDni());
        assertNull(booking.get(0).getRenter().getLikedCar());

        assertEquals("VSSZZZ6KZ1R149943", booking.get(0).getVehicleList().get(0).getVinNumber());
        assertEquals(0, booking.get(0).getVehicleList().get(0).getDailyCost().compareTo(new BigDecimal("50")));
        assertEquals(25400, booking.get(0).getVehicleList().get(0).getKilometersAmount());
        assertEquals(Boolean.TRUE, booking.get(0).getVehicleList().get(0).getGoodCondition());
    }

    @Test
    void testDelete() {
        List<Booking> booking = this.bookingPersistenceMongodb.readByRenterName("Alejandro").collect(Collectors.toList());
        assertEquals("1404", booking.get(0).getBookingNumber());

        this.bookingPersistenceMongodb.delete("1404");
        assertThrows(NotFoundException.class, () -> this.bookingPersistenceMongodb.readByRenterName("Alejandro"));
    }
}
