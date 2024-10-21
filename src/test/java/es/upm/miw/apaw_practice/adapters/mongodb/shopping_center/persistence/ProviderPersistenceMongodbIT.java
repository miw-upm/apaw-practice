package es.upm.miw.apaw_practice.adapters.mongodb.shopping_center.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.shopping_center.Provider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class ProviderPersistenceMongodbIT {

    @Autowired
    private ProviderPersistenceMongodb providerPersistenceMongodb;

    @Test
    void testNameNotExist() {
        assertFalse(this.providerPersistenceMongodb.existName("no_name"));
    }

    @Test
    void testNameExist() {
        assertTrue(this.providerPersistenceMongodb.existName("provider2"));
    }

    @Test
    void testCreate() {
        Provider provider = new Provider("provider4", "drinks", false);
        Provider providerResult = this.providerPersistenceMongodb.create(provider);
        assertEquals("provider4", providerResult.getName());
        assertEquals("drinks", providerResult.getMainService());
        assertFalse(providerResult.isNational());
    }
}
