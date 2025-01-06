package es.upm.miw.apaw_practice.adapters.mongodb.hotel.persitencia;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel.persistence.HotelReservationPersistenceMongodb;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.hotel.HotelMain;
import es.upm.miw.apaw_practice.domain.models.hotel.HotelReservation;
import es.upm.miw.apaw_practice.domain.models.hotel.HotelRoom;
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
    void testPatchReservationRoom(){
        String roomNumber = "202";
        LocalDate date = null;
        HotelReservation reservationPatched = this.hotelReservationPersistenceMongodb.patchReservation("1", roomNumber, date);
        LocalDate nonModifiedDate = LocalDate.of(2020,1,1);
        assertEquals("202", reservationPatched.getRoomNumber());
        assertEquals(nonModifiedDate, reservationPatched.getReservationDate());
    }

    @Test
    void testPatchReservationDate(){
        String roomNumber = null;
        LocalDate date = LocalDate.of(1999,1,1);
        HotelReservation reservationPatched = this.hotelReservationPersistenceMongodb.patchReservation("2", roomNumber, date);
        assertEquals("202", reservationPatched.getRoomNumber());
        assertEquals(date, reservationPatched.getReservationDate());
    }
    @Test
    void testPatchReservationRoomDate(){
        String roomNumber = "666";
        LocalDate date = LocalDate.of(1999,1,1);
        HotelReservation reservationPatched = this.hotelReservationPersistenceMongodb.patchReservation("3", roomNumber, date);
        assertEquals("666", reservationPatched.getRoomNumber());
        assertEquals(date, reservationPatched.getReservationDate());
    }
}
