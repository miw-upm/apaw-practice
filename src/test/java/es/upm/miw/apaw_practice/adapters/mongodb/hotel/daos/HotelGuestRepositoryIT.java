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
    void testCreateAndRead() {
        assertTrue(this.hotelGuestRepository.findAll().stream()
                .anyMatch(hotelGuest -> "88888888K".equals(hotelGuest.getDni()) &&
                        "Mario".equals(hotelGuest.getName()) &&
                        hotelGuest.getEntryDate() != null &&
                        hotelGuest.getDepartureDate() != null
                ));
    }


}
