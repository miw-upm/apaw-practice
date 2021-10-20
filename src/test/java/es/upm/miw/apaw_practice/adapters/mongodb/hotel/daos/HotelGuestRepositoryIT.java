package es.upm.miw.apaw_practice.adapters.mongodb.hotel.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities.HotelGuestEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.hotel.HotelGuest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void testFindByName() {
        List<HotelGuestEntity> hotelGuestEntity = this.hotelGuestRepository.findByName("Mario");
        assertEquals(List.of("88888888K"), hotelGuestEntity.stream()
                .map(HotelGuestEntity::getDni)
                .distinct()
                .collect(Collectors.toList()));

    }

}
