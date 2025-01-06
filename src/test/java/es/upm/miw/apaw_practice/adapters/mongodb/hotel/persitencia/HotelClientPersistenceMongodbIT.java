package es.upm.miw.apaw_practice.adapters.mongodb.hotel.persitencia;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel.persistence.HotelClientPersistenceMongodb;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
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
    void testCreate (){
        String rNumber = "1";
        HotelReservation reservation = new HotelReservation();
        reservation.setReservationNumber(rNumber);
        HotelClient newClient = new HotelClient("x5201314x", "Mengtxu", "677777777", "Mengtxu@gmail.com", reservation);
        HotelClient client = this.hotelClientPersistenceMongodb.create(newClient);
        assertEquals(client.getName(), "Mengtxu");
        assertEquals(client.getPhone(), "677777777");
        assertEquals("1", client.getReservation().getReservationNumber());
    }

}
