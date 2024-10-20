package es.upm.miw.apaw_practice.adapters.mongodb.gun_store.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.gun_store.entities.GunEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class GunRepositoryIT {
    @Autowired
    private GunRepository gunRepository;

    @Test
    public void testFindByName() {
        assertTrue(this.gunRepository.findByName("Beretta 92FS").isPresent());
        GunEntity actualGun = this.gunRepository.findByName("Beretta 92FS").get();
        assertEquals("Beretta", actualGun.getManufacturer());
        assertEquals(new BigDecimal("849.99"), actualGun.getPrice());
        assertEquals(2, actualGun.getAccesoryEntities().size());
        assertNotNull(actualGun.getAmmoEntity());

    }
}
