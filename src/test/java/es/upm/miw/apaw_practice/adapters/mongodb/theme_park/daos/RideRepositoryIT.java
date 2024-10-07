package es.upm.miw.apaw_practice.adapters.mongodb.theme_park.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.theme_park.entities.RideEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.theme_park.entities.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
class RideRepositoryIT {

    @Autowired
    private RideRepository rideRepository;

    @Test
    void testCreateAndRead() {
        assertTrue(this.rideRepository.findByName("Shambala").isPresent());
        RideEntity ride = this.rideRepository.findByName("Shambala").get();
        assertEquals("Shambala", ride.getName());
        assertEquals("Halloween", ride.getTheme());
        assertTrue(ride.getUserEntities().stream()
                .map(UserEntity::getIdMembership)
                .toList()
                .containsAll(Arrays.asList("712", "41")));
        assertTrue(ride.getFavourite());

    }
}
