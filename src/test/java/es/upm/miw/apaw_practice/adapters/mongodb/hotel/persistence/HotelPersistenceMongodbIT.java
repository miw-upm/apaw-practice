package es.upm.miw.apaw_practice.adapters.mongodb.hotel.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
public class HotelPersistenceMongodbIT {
    @Autowired
    private HotelPersistenceMongodb hotelPersistenceMongodb;

    @Test
    public void testFindHotelById() {
        assertEquals("77777777V", this.hotelPersistenceMongodb.read("1").getDirector().getDni());
        assertEquals(3, this.hotelPersistenceMongodb.read("1").getRooms().get(0).getNumber());
        assertEquals("Av. Luto, 23981", this.hotelPersistenceMongodb.read("1").getDirection());
    }
}
