package es.upm.miw.apaw_practice.domain.services.hotel;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.hotel.HotelReservation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class HotelReservationServiceIT {
    @Autowired
    private HotelReservationService hotelReservationService;

    @Test
    void testPatchReservationRoom(){
        String roomNumber = "202";
        LocalDate date = null;
        HotelReservation reservationPatched = this.hotelReservationService.patchReservation("1", roomNumber, date);
        LocalDate nonModifiedDate = LocalDate.of(2020,1,1);
        assertEquals("202", reservationPatched.getRoomNumber());
        assertEquals(nonModifiedDate, reservationPatched.getReservationDate());
    }

    @Test
    void testPatchReservationDate(){
        String roomNumber = null;
        LocalDate date = LocalDate.of(1999,1,1);
        HotelReservation reservationPatched = this.hotelReservationService.patchReservation("2", roomNumber, date);
        assertEquals("202", reservationPatched.getRoomNumber());
        assertEquals(date, reservationPatched.getReservationDate());
    }
    @Test
    void testPatchReservationRoomDate(){
        String roomNumber = "666";
        LocalDate date = LocalDate.of(1999,1,1);
        HotelReservation reservationPatched = this.hotelReservationService.patchReservation("3", roomNumber, date);
        assertEquals("666", reservationPatched.getRoomNumber());
        assertEquals(date, reservationPatched.getReservationDate());
    }
}
