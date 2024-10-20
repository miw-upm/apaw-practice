package es.upm.miw.apaw_practice.domain.services.hotel;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.hotel.HotelClient;
import es.upm.miw.apaw_practice.domain.models.hotel.HotelReservation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
public class HotelClientServiceIT {

    @Autowired
    private HotelClientService hotelClientService;

    @Test
    void create() {
        HotelReservation reservation = new HotelReservation("6", "202", LocalDate.of(2020, 1, 5));
        HotelClient client = this.hotelClientService.create(new HotelClient("x6666666x", "Mengtxu", "677777777", "Mengtxu@gmail.com", reservation));
        assertEquals("x6666666x", client.getIdentityDocument());
        assertEquals("Mengtxu", client.getName());
    }
}
