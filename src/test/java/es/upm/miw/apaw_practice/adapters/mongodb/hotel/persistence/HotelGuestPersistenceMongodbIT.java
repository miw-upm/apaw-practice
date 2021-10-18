package es.upm.miw.apaw_practice.adapters.mongodb.hotel.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.hotel.HotelGuest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
class HotelGuestPersistenceMongodbIT {

    @Autowired
    private HotelGuestPersistenceMongodb hotelGuestPersistence;

    @Test
    void testCreateAndRead() {
        LocalDateTime entryDate = LocalDateTime.of(2021, 5, 1, 9, 0);
        LocalDateTime departureDate = LocalDateTime.of(2021, 5, 30, 16, 0);

        HotelGuest hotelGuest = HotelGuest.builder()
                .dni("11111111P")
                .name("Kino")
                .entryDate(entryDate)
                .departureDate(departureDate)
                .build();
        this.hotelGuestPersistence.create(hotelGuest);

        HotelGuest hotelGuestDB = this.hotelGuestPersistence.readByDni("11111111P");
        assertEquals("Kino", hotelGuestDB.getName());
        assertEquals(entryDate, hotelGuestDB.getEntryDate());
        assertEquals(departureDate, hotelGuestDB.getDepartureDate());
    }


}
