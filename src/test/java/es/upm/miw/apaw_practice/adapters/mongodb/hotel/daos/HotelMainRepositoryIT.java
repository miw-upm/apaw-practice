package es.upm.miw.apaw_practice.adapters.mongodb.hotel.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities.HotelMainEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class HotelMainRepositoryIT {

    @Autowired
    private HotelMainRepository hotelMainRepository;

    @Test
    void testFindByName() {
        HotelMainEntity hotelMainEntity = this.hotelMainRepository.findByName("xiangHotel").get();
        assertTrue(this.hotelMainRepository.findByName("xiangHotel").isPresent());
        assertEquals("xiangHotel", hotelMainEntity.getName());
        assertEquals("Street God", hotelMainEntity.getAddress());
    }

}
