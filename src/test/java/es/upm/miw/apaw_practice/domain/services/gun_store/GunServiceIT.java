package es.upm.miw.apaw_practice.domain.services.gun_store;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.gun_store.Accesory;
import es.upm.miw.apaw_practice.domain.models.gun_store.CompatibleAmmo;
import es.upm.miw.apaw_practice.domain.models.gun_store.Gun;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
public class GunServiceIT {

    public static final String AK_47 = "AK-47";
    @Autowired
    GunService gunService;

    @Test
    void testRead() {
        Accesory accesory1 = new Accesory(4, "Silencer", new BigDecimal("299.99"));
        Accesory accesory2 = new Accesory(1, "Scope", new BigDecimal("199.99"));
        CompatibleAmmo compatibleAmmo = new CompatibleAmmo(4, new BigDecimal("29.99"), "7.62x39mm");
        Gun expectedGun = new Gun(new BigDecimal("1299.99"), "AK-47", "Kalashnikov", Arrays.asList(accesory1, accesory2), compatibleAmmo);
        assertEquals(expectedGun, this.gunService.read(AK_47));
    }
}
