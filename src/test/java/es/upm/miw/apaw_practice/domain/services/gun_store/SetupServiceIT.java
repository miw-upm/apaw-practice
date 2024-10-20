package es.upm.miw.apaw_practice.domain.services.gun_store;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.gun_store.GunStoreSeederService;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.gun_store.Accesory;
import es.upm.miw.apaw_practice.domain.models.gun_store.CompatibleAmmo;
import es.upm.miw.apaw_practice.domain.models.gun_store.Gun;
import es.upm.miw.apaw_practice.domain.models.gun_store.Setup;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class SetupServiceIT {

    @Autowired
    SetupService setupService;

    @Test
    public void testDelete() {
        Integer id = 0;
        assertDoesNotThrow(() -> this.setupService.read(id));
        assertNotNull(this.setupService.read(id));
        this.setupService.delete(id);
        assertThrows(NotFoundException.class, () -> this.setupService.read(id));
    }

    @Test
    public void testCreate() {
        Setup expectedCreatedSetup = GunStoreSeederService.getNewDummySetup();
        Setup actualCreatedSetup = this.setupService.create(expectedCreatedSetup);
        assertEquals(expectedCreatedSetup.getSetupId(), actualCreatedSetup.getSetupId());
        assertEquals(expectedCreatedSetup.getTotalPrice(), actualCreatedSetup.getTotalPrice());
        assertEquals(expectedCreatedSetup.getGuns(), actualCreatedSetup.getGuns());
    }

    @Test
    public void testFindMostExpensiveByCaliberAndCategory() {
        Setup actualMostExpensive = setupService
                .findMostExpensiveByCaliberAndCategory("7.62x39mm", "Scope");
        assertEquals(4, actualMostExpensive
                .getSetupId());
        assertEquals(new BigDecimal("4299.98"), actualMostExpensive.getTotalPrice());
    }

}
