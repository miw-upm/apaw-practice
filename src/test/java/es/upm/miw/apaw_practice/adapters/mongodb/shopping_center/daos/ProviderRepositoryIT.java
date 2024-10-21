package es.upm.miw.apaw_practice.adapters.mongodb.shopping_center.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.shopping_center.entities.ProviderEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
class ProviderRepositoryIT {

    @Autowired
    private ProviderRepository providerRepository;

    @Test
    void testFindByName() {
        assertTrue(this.providerRepository.findByName("provider3").isPresent());
        ProviderEntity providerEntity = this.providerRepository.findByName("provider3").get();
        assertEquals("clothes", providerEntity.getMainService());
        assertTrue(providerEntity.isNational());
    }
}
