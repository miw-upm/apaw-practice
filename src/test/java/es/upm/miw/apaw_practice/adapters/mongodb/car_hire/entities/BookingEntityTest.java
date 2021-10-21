package es.upm.miw.apaw_practice.adapters.mongodb.car_hire.entities;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.car_hire.daos.BookingRepository;
import es.upm.miw.apaw_practice.domain.models.car_hire.Booking;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class BookingEntityTest {

    @Autowired
    BookingRepository bookingRepository;

    @Test
    void testToBooking() {
        BookingEntity bookingEntity = this.bookingRepository.findAll().get(0);
        Booking booking = bookingEntity.toBooking();

        assertEquals(bookingEntity.getBookingNumber(), booking.getBookingNumber());
        assertEquals(bookingEntity.getHiredDate(), booking.getHiredDate());
        assertEquals(bookingEntity.getNumberDays(), booking.getNumberDays());

        assertEquals(bookingEntity.getRenterEntity().getName(), booking.getRenter().getName());
        assertEquals(bookingEntity.getRenterEntity().getDni(), booking.getRenter().getDni());
        assertNotNull(bookingEntity.getRenterEntity().getId());
        assertNull(bookingEntity.getRenterEntity().getLikedCar());
        assertNull(booking.getRenter().getLikedCar());

        assertEquals(bookingEntity.getVehicleEntities().get(0).getId(), booking.getVehicleList().get(0).getId());
        assertEquals(bookingEntity.getVehicleEntities().get(0).getVinNumber(), booking.getVehicleList().get(0).getVinNumber());
        assertEquals(bookingEntity.getVehicleEntities().get(0).getDailyCost(), booking.getVehicleList().get(0).getDailyCost());
        assertEquals(bookingEntity.getVehicleEntities().get(0).getKilometersAmount(), booking.getVehicleList().get(0).getKilometersAmount());
        assertEquals(bookingEntity.getVehicleEntities().get(0).getGoodCondition(), booking.getVehicleList().get(0).getGoodCondition());
    }
}
