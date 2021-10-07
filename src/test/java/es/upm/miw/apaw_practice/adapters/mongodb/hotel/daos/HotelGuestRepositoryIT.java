package es.upm.miw.apaw_practice.adapters.mongodb.hotel.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities.HotelGuestEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
class HotelGuestRepositoryIT {

    @Autowired
    private HotelGuestRepository hotelGuestRepository;

    @Test
    void testFindByDni() {
        assertTrue(this.hotelGuestRepository.findByDni("25252525R").isPresent());
        HotelGuestEntity hotelGuest = this.hotelGuestRepository.findByDni("25252525R").get();
        assertEquals("Laura", hotelGuest.getName());
        assertEquals(LocalDateTime.of(2020, 6, 15, 9, 0), hotelGuest.getEntryDate());
        assertEquals(LocalDateTime.of(2018, 9, 16, 16, 0), hotelGuest.getDepartureDate());
    }

    @Test
    void testFindByName() {
        assertTrue(this.hotelGuestRepository.findByName("Luca").isPresent());
        HotelGuestEntity hotelGuest = this.hotelGuestRepository.findByName("Luca").get();
        assertEquals("56565656P", hotelGuest.getDni());
        assertEquals(LocalDateTime.of(2020, 10, 6, 12, 0), hotelGuest.getEntryDate());
        assertEquals(LocalDateTime.of(2020, 10, 12, 18, 0), hotelGuest.getDepartureDate());
    }

    @Test
    void testCreateAndRead() {
        assertTrue(this.hotelGuestRepository.findAll().stream()
                .anyMatch(hotelGuest -> "88888888K".equals(hotelGuest.getDni()) &&
                        "Mario".equals(hotelGuest.getName()) &&
                        hotelGuest.getEntryDate() != null &&
                        hotelGuest.getDepartureDate() != null
                ));
    }


}
