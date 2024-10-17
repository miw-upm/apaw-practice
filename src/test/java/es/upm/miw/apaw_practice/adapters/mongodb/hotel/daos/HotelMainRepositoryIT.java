package es.upm.miw.apaw_practice.adapters.mongodb.hotel.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities.HotelMainEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class HotelMainRepositoryIT {

    @Autowired
    private HotelMainRepository hotelMainRepository;

    @Test
    void testFindByName() {
        assertTrue(this.hotelMainRepository.findByName("xiangHotel").isPresent());
        HotelMainEntity hotelMainEntity = this.hotelMainRepository.findByName("xiangHotel").get();
        assertEquals("xiangHotel", hotelMainEntity.getName());
        assertEquals("Street God", hotelMainEntity.getAddress());
    }

}
