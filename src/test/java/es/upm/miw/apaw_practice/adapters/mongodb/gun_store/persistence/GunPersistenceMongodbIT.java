package es.upm.miw.apaw_practice.adapters.mongodb.gun_store.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.gun_store.GunStoreSeederService;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.gun_store.Gun;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class GunPersistenceMongodbIT {
    @Autowired
    private GunPersistenceMongodb gunPersistenceMongodb;
    @Test
    public void testNameNotFound() {
        assertThrows(NotFoundException.class, () -> this.gunPersistenceMongodb.readByName("Jabali"));
    }

    @Test
    public void testReadByName() {
        Gun actual = this.gunPersistenceMongodb.readByName("Beretta 92FS");
        assertEquals(5, actual.getGunId());
        assertEquals("Beretta", actual.getManufacturer());
        assertEquals(new BigDecimal("849.99"), actual.getPrice());
        assertEquals(2, actual.getAccesories().size());
        assertNotNull(actual.getAmmo());

    }

    @Test
    public void testUpdate() {
        Integer idOfUpdated = 2;
        Gun newGun = GunStoreSeederService.getNewDummyGun(2);
        assertEquals(idOfUpdated, this.gunPersistenceMongodb.readByName("AR-15").getGunId());
        assertNotEquals(newGun, this.gunPersistenceMongodb.readByName("AR-15"));
        gunPersistenceMongodb.update(idOfUpdated, newGun);
        assertEquals(idOfUpdated, this.gunPersistenceMongodb.readByName(newGun.getName()).getGunId());
        assertEquals(newGun, this.gunPersistenceMongodb.readByName(newGun.getName()));
    }
}
