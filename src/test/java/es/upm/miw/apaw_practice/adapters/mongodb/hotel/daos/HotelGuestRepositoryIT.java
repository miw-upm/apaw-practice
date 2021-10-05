package es.upm.miw.apaw_practice.adapters.mongodb.hotel.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities.HotelGuestEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class HotelGuestRepositoryIT {

    @Autowired
    private HotelGuestRepository hotelGuestRepository;

    @Test
    public void testFindByDni() {
        assertTrue(this.hotelGuestRepository.findByDniGuest("25252525R").isPresent());
        HotelGuestEntity hotelGuest = this.hotelGuestRepository.findByDniGuest("25252525R").get();
        assertEquals("Laura", hotelGuest.getNameGuest());
        assertEquals(LocalDateTime.of(2020, 6, 15, 9, 0), hotelGuest.getEntryDate());
        assertEquals(LocalDateTime.of(2018, 9, 16, 16, 0), hotelGuest.getDepartureDate());
    }

    @Test
    public void testFindByName() {
        assertTrue(this.hotelGuestRepository.findByNameGuest("Luca").isPresent());
        HotelGuestEntity hotelGuest = this.hotelGuestRepository.findByNameGuest("Luca").get();
        assertEquals("56565656P", hotelGuest.getDniGuest());
        assertEquals(LocalDateTime.of(2020, 10, 6, 12, 0), hotelGuest.getEntryDate());
        assertEquals(LocalDateTime.of(2020, 10, 12, 18, 0), hotelGuest.getDepartureDate());
    }

    @Test
    public void testCreateAndRead() {
        assertTrue(this.hotelGuestRepository.findAll().stream()
                .anyMatch(hotelGuest -> "88888888K".equals(hotelGuest.getDniGuest()) &&
                        "Mario".equals(hotelGuest.getNameGuest()) &&
                        hotelGuest.getEntryDate() != null &&
                        hotelGuest.getDepartureDate() != null
                ));
    }


}
