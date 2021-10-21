package es.upm.miw.apaw_practice.domain.models.hotel;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

@TestConfig
public class HotelGuestTest {
    @Test
    void testBuilder() {
        LocalDateTime entryDate = LocalDateTime.of(2005, 6, 21, 8, 30);
        LocalDateTime departureDate = LocalDateTime.of(2005, 6, 29, 16, 30);

        HotelGuest hotelGuest = HotelGuest.builder()
                .dni("11111111O")
                .name("Alex")
                .entryDate(entryDate)
                .departureDate(departureDate)
                .build();
        Assertions.assertEquals("11111111O", hotelGuest.getDni());
        Assertions.assertEquals("Alex", hotelGuest.getName());
        Assertions.assertEquals(entryDate, hotelGuest.getEntryDate());
        Assertions.assertEquals(departureDate, hotelGuest.getDepartureDate());

    }
}
