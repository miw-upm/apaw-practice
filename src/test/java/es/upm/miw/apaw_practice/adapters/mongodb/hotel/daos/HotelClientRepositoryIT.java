package es.upm.miw.apaw_practice.adapters.mongodb.hotel.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities.HotelClientEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class HotelClientRepositoryIT {

    @Autowired
    private HotelClientRepository hotelClientRepository;

    @Test
    void testFindByIdentityDocument() {
        HotelClientEntity hotelClientEntity = this.hotelClientRepository.findByIdentityDocument("y1111111x").get();
        assertTrue(this.hotelClientRepository.findByIdentityDocument("y1111111x").isPresent());
        assertEquals("David", hotelClientEntity.getName());
        assertEquals("y1111111x", hotelClientEntity.getIdentityDocument());
    }

}
