package es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities;

import es.upm.miw.apaw_practice.adapters.mongodb.hotel.daos.HotelGuestRepository;
import es.upm.miw.apaw_practice.domain.models.hotel.HotelGuest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class HotelGuestEntityTest {

    @Autowired
    private HotelGuestRepository hotelGuestRepository;

    @Test
    void testBuilderHotelGuest() {
        LocalDateTime entryDate = LocalDateTime.of(2021, 1, 10, 9, 5);
        LocalDateTime departureDate = LocalDateTime.of(2021, 1, 20, 9, 5);

        HotelGuest hotelGuest = HotelGuest.builder()
                .dni("88888822P")
                .name("Grego")
                .entryDate(entryDate)
                .departureDate(departureDate)
                .build();

        HotelGuestEntity hotelGuestEntity = new HotelGuestEntity(hotelGuest);

        Assertions.assertNotNull(hotelGuestEntity.getId());
        Assertions.assertEquals("88888822P", hotelGuestEntity.getDni());
        Assertions.assertEquals("Grego", hotelGuestEntity.getName());
        Assertions.assertEquals(entryDate, hotelGuestEntity.getEntryDate());
        Assertions.assertEquals(departureDate, hotelGuestEntity.getDepartureDate());

    }
}
