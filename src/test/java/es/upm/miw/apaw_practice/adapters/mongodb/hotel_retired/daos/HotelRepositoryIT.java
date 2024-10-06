package es.upm.miw.apaw_practice.adapters.mongodb.hotel_retired.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel_retired.entities.HotelEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class HotelRepositoryIT {

    @Autowired
    private HotelRepository hotelRepository;

    @Test
    void testFindByCif() {
        assertTrue(this.hotelRepository.findByCif("F91635847").isPresent());
        HotelEntity hotelEntity = this.hotelRepository.findByCif("F91635847").get();
        assertEquals("LaMaria", hotelEntity.getHotelName());
        assertEquals("C/ Mandrágora 32, Retuerta (Burgos)", hotelEntity.getAddress());
    }
}
