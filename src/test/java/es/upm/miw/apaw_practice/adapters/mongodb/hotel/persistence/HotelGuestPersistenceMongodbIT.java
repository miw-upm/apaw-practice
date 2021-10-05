package es.upm.miw.apaw_practice.adapters.mongodb.hotel.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.hotel.HotelGuest;
import es.upm.miw.apaw_practice.domain.models.shop.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class HotelGuestPersistenceMongodbIT {

    @Autowired
    private HotelGuestPersistenceMongodb hotelGuestPersistence;

    @Test
    void testCreateAndRead(){
        LocalDateTime entryDate = LocalDateTime.of(2021, 5, 1, 9, 0);
        LocalDateTime departureDate =  LocalDateTime.of(2021, 5, 30, 16, 0);

        HotelGuest hotelGuest = new HotelGuest("Kino", "11111111P",entryDate,departureDate);
        this.hotelGuestPersistence.create(hotelGuest);

        HotelGuest hotelGuestDB = this.hotelGuestPersistence.read("11111111P");
        assertEquals("Kino", hotelGuestDB.getNameGuest());
        assertEquals(entryDate, hotelGuestDB.getEntryDate());
        assertEquals(departureDate, hotelGuestDB.getDepartureDate());

    }
}
