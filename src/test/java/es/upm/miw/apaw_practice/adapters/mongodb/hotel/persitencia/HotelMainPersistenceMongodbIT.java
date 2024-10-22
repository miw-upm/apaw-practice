package es.upm.miw.apaw_practice.adapters.mongodb.hotel.persitencia;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel.persistence.HotelMainPersistenceMongodb;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.hotel.HotelMain;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class HotelMainPersistenceMongodbIT {

    @Autowired
    HotelMainPersistenceMongodb hotelMainPersistenceMongodb;

    @Test
    void testFindByName() {
        HotelMain hotelMain = this.hotelMainPersistenceMongodb.findByName("xiangHotel");
        assertEquals("xiangHotel", hotelMain.getName());
        assertEquals("966666666",hotelMain.getPhone());
    }
    @Test
    void testNotFound() {
        assertThrows(NotFoundException.class, () -> this.hotelMainPersistenceMongodb.findByName("algo"));
    }

    @Test
    void testDelete (){
        this.hotelMainPersistenceMongodb.delete("mengfeiHotel");
        assertThrows(NotFoundException.class, () -> this.hotelMainPersistenceMongodb.findByName("mengfeiHotel"));
    }

}
