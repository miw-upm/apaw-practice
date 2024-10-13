package es.upm.miw.apaw_practice.adapters.mongodb.shopping_center.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.shopping_center.Shop;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class ShopPersistenceMongodbIT {

    @Autowired
    private ShopPersistenceMongodb shopPersistenceMongodb;

    @Test
    void testReadAll() {
        Optional<Shop> shop = this.shopPersistenceMongodb.readAll()
                .filter(sh -> "shop1".equals(sh.getName()))
                .findFirst();
        assertTrue(shop.isPresent());
        assertNotNull(shop.get().getId());
    }
}
