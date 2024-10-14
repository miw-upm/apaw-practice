package es.upm.miw.apaw_practice.adapters.mongodb.night_life.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.night_life.Owner;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
class OwnerClinicNightLifePersistenceMongodbIT {
    @Autowired
    private OwnerNightLifePersistenceMongodb ownerPersistence;
    @Test
    void testCreate() {
        Owner owner =
                new Owner("Raul", "123456789", "raul@example.com");
        Owner ownerCreated = this.ownerPersistence.create(owner);
        assertNotNull(ownerCreated);
        assertEquals("Raul", ownerCreated.getName());
        assertEquals("123456789", ownerCreated.getPhone());
        assertEquals("raul@example.com", ownerCreated.getEmail());

    }
}