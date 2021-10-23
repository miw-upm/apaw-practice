package es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.entities.OwnerEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class OwnerRepositoryIT {

    @Autowired
    private OwnerRepository ownerRepository;

    @Test
    void testFindByDni() {
        assertTrue(this.ownerRepository.findByDni("00000000Z").isPresent());
        OwnerEntity owner = this.ownerRepository.findByDni("00000000Z").get();
        assertEquals("00000000Z", owner.getDni());
        assertEquals("John Doe", owner.getName());
    }

    @Test
    void testFindByName() {
        assertTrue(this.ownerRepository.findByName("John Doe").isPresent());
        OwnerEntity owner = this.ownerRepository.findByName("John Doe").get();
        assertEquals("00000000Z", owner.getDni());
        assertEquals("John Doe", owner.getName());
    }
}
