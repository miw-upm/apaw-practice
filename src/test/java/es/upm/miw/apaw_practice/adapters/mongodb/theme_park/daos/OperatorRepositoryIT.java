package es.upm.miw.apaw_practice.adapters.mongodb.theme_park.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.theme_park.entities.OperatorEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.theme_park.entities.RideEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.theme_park.entities.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
class OperatorRepositoryIT {

    @Autowired
    private OperatorRepository operatorRepository;

    @Test
    void testFindByIdEmployee() {
        Optional<OperatorEntity> operatorEntity = operatorRepository.findByIdEmployee("65123A");
        assertFalse(operatorEntity.isEmpty());
        assertNotNull(operatorEntity.get());
        OperatorEntity operator = operatorEntity.get();
        assertEquals("Jude", operator.getNick());
        assertEquals(LocalDateTime.of(2018,3,10,13,1), operator.getRegistrationDate());
        RideEntity ride = operator.getRideEntity();
        assertEquals("Dragon Khan", ride.getName());
        assertEquals("Disney", ride.getTheme());
        assertTrue(ride.getUserEntities().stream()
                .map(UserEntity::getIdMembership)
                .toList()
                .contains("1252"));
        assertFalse(ride.getFavourite());
    }
}
