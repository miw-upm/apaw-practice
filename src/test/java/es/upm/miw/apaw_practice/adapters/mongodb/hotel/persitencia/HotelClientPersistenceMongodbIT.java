package es.upm.miw.apaw_practice.adapters.mongodb.hotel.persitencia;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel.persistence.HotelClientPersistenceMongodb;
import es.upm.miw.apaw_practice.domain.models.hotel.HotelClient;
import es.upm.miw.apaw_practice.domain.models.hotel.HotelReservation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class HotelClientPersistenceMongodbIT {

    @Autowired
    HotelClientPersistenceMongodb hotelClientPersistenceMongodb;

    @Test
    void testExistDNI() {
        boolean exist = this.hotelClientPersistenceMongodb.existDNI("y1111111x");
        assertTrue(exist);
    }

    @Test
    void testNotExistDNI() {
        boolean exist = this.hotelClientPersistenceMongodb.existDNI("y4444444x");
        assertFalse(exist);
    }

    @Test
    void testExistReservationNumber() {
        boolean exist = this.hotelClientPersistenceMongodb.existReservationNumber("1");
        assertTrue(exist);
    }

    @Test
    void testNotExistReservationNumber() {
        boolean exist = this.hotelClientPersistenceMongodb.existReservationNumber("10");
        assertFalse(exist);
    }


    @Test
    void testCreate (){
        HotelReservation reservation = new HotelReservation("5", "101", LocalDate.of(2020,1,1));
        HotelClient newClient= new HotelClient("Y1234567X", "Oscar","123456789", "", reservation);
        HotelClient client = this.hotelClientPersistenceMongodb.create(newClient);
        assertEquals(client.getName(), "Oscar");
        assertEquals(client.getPhone(), "123456789");
        assertEquals(reservation.getReservationDate(), client.getReservation().getReservationDate());
    }

}
