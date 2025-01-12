package es.upm.miw.apaw_practice.domain.services.hotel;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.hotel.HotelClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
public class HotelClientServiceIT {

    @Autowired
    private HotelClientService hotelClientService;

    @Test
    void create() {
        HotelClient newClient = new HotelClient("x6666666x", "Mengtxu", "677777777", "Mengtxu@gmail.com");
        HotelClient client = this.hotelClientService
                .create(newClient);
        assertEquals("x6666666x", client.getIdentityDocument());
        assertEquals("Mengtxu", client.getName());
    }
}
