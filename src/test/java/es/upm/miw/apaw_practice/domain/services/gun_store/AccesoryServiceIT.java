package es.upm.miw.apaw_practice.domain.services.gun_store;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.gun_store.AccesoryPriceUpdating;
import es.upm.miw.apaw_practice.domain.persistence_ports.gun_store.AccesoryPersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
public class AccesoryServiceIT {

    @Autowired
    AccesoryService accesoryService;

    @Autowired
    AccesoryPersistence accesoryPersistence;

    @Test
    void testUpdatePrice() {
        AccesoryPriceUpdating accesoryPriceUpdating = new AccesoryPriceUpdating(2, new BigDecimal("49.95"));
        this.accesoryService.updatePrice(accesoryPriceUpdating);
        assertEquals(0, new BigDecimal("49.95").compareTo(this.accesoryPersistence.read(2).getPrice()));
        this.accesoryService.updatePrice(new AccesoryPriceUpdating(2, new BigDecimal("49.99")));
    }
}
