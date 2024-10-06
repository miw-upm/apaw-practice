package es.upm.miw.apaw_practice.adapters.mongodb.hotel_retired.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel_retired.entities.GuestEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class GuestRepositoryIT {

    @Autowired
    private GuestRepository guestRepository;

    @Test
    void testFindByNif() {
        assertTrue(this.guestRepository.findByNif("99527370E").isPresent());
        GuestEntity guestEntity = this.guestRepository.findByNif("99527370E").get();
        assertEquals("Emilio Pedrajas", guestEntity.getFullName());
        assertEquals(LocalDateTime.of(1990, 10, 27,  23, 2, 2 ), guestEntity.getBirthDay());
    }
}
