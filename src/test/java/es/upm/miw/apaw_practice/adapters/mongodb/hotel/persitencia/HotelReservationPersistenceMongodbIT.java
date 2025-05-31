package es.upm.miw.apaw_practice.adapters.mongodb.hotel.persitencia;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel.persistence.HotelReservationPersistenceMongodb;
import es.upm.miw.apaw_practice.domain.models.hotel.HotelClient;
import es.upm.miw.apaw_practice.domain.models.hotel.HotelReservation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class HotelReservationPersistenceMongodbIT {

    @Autowired
    HotelReservationPersistenceMongodb hotelReservationPersistenceMongodb;

    @Test
    void testPatchReservationRoom() {
        String roomNumber = "202";
        LocalDate date = null;
        HotelClient client = null;
        HotelReservation reservation = new HotelReservation("1", roomNumber, date, client);
        HotelReservation reservationPatched = this.hotelReservationPersistenceMongodb.patchReservation("1", reservation);
        LocalDate nonModifiedDate = LocalDate.of(2020,1,1);
        assertEquals("202", reservationPatched.getRoomNumber());
        assertEquals(nonModifiedDate, reservationPatched.getReservationDate());
    }

    @Test
    void testPatchReservationDate() {
        String roomNumber = null;
        LocalDate date = LocalDate.of(1999,1,1);
        HotelClient client = null;
        HotelReservation reservation = new HotelReservation("2", roomNumber, date, client);
        HotelReservation reservationPatched = this.hotelReservationPersistenceMongodb.patchReservation("2", reservation);
        assertEquals("202", reservationPatched.getRoomNumber());
        assertEquals(date, reservationPatched.getReservationDate());
    }

    @Test
    void testPatchReservationClient() {
        String roomNumber = null;
        LocalDate date = null;
        LocalDate nonModifiedDate = LocalDate.of(2020,10,12);
        HotelClient client = new HotelClient("x6666666x", "Alex", "666666666", "test@gmail.com");
        HotelReservation reservation = new HotelReservation("3", roomNumber, date, client);
        HotelReservation reservationPatched = this.hotelReservationPersistenceMongodb.patchReservation("3", reservation);
        assertEquals("303", reservationPatched.getRoomNumber());
        assertEquals(nonModifiedDate, reservationPatched.getReservationDate());
        assertEquals("x6666666x", reservationPatched.getClient().getIdentityDocument());
        assertEquals("Alex", reservationPatched.getClient().getName());
        assertEquals("666666666", reservationPatched.getClient().getPhone());
        assertEquals("test@gmail.com", reservationPatched.getClient().getEmail());
    }

    @Test
    void testFindSumTotalPriceByReservationDate() {
        LocalDate date = LocalDate.of(2023,10,12);
        assertEquals(new BigDecimal("200.00"),hotelReservationPersistenceMongodb.findSumTotalPriceByReservationDate(date));
    }

}
