package es.upm.miw.apaw_practice.adapters.mongodb.gun_store.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.gun_store.GunStoreSeederService;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.gun_store.Setup;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
public class SetupPersistenceMongodbIT {

    @Autowired
    private SetupPersistenceMongodb setupPersistenceMongodb;

    @Test
    public void testDelete() {
        Assertions.assertDoesNotThrow(() -> this.setupPersistenceMongodb.read(1));
        this.setupPersistenceMongodb.delete(1);
        Assertions.assertThrows(NotFoundException.class, () -> this.setupPersistenceMongodb.read(1));
    }

    @Test
    public void testReadNotFound() {
        Assertions.assertThrows(NotFoundException.class, () -> this.setupPersistenceMongodb.read(-1));
    }

    @Test
    public void testCreate() {
        Setup newSetup = GunStoreSeederService.getNewDummySetup();
        Assertions.assertDoesNotThrow(() -> this.setupPersistenceMongodb.create(newSetup));
        Setup actualCreatedSetup = this.setupPersistenceMongodb.read(newSetup.getSetupId());
        assertEquals(newSetup, actualCreatedSetup);
    }

    @Test
    public void testFindAll() {
        assertEquals(5, this.setupPersistenceMongodb.findAll().count());
    }
}
