package es.upm.miw.apaw_practice.domain.services.hotel;


import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.hotel.HotelMain;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestConfig
public class HotelMainServiceIT {

    @Autowired
    private HotelMainService hotelMainService;

    @Test
    void findByName() {
        HotelMain hotel = this.hotelMainService.findByName("xiangHotel");
        assertEquals("966666666", hotel.getPhone());
    }

    @Test
    void delete(){
        this.hotelMainService.delete("mengfeiHotel");
        assertThrows(NotFoundException.class, () -> this.hotelMainService.findByName("mengfeiHotel"));
    }

}
