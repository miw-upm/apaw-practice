package es.upm.miw.apaw_practice.adapters.mongodb.gun_store.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.gun_store.Accesory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestConfig
public class AccesoryPersistenceMongodbIT {
    @Autowired
    private AccesoryPersistenceMongodb accesoryPersistenceMongodb;

    @Test
    void testUpdatePrice() {
        Accesory newAccesory = new Accesory(1, "Magazine", new BigDecimal("24.99"));
        this.accesoryPersistenceMongodb.update(1, newAccesory);
        Accesory actualUpdatedObject = this.accesoryPersistenceMongodb.read(1);
        assertEquals(newAccesory, actualUpdatedObject);
    }

    @Test
    void testNotFound() {
        assertThrows(NotFoundException.class, () -> this.accesoryPersistenceMongodb.read(-1));
    }
}
