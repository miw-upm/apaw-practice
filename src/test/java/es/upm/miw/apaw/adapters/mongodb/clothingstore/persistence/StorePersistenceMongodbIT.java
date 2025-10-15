package es.upm.miw.apaw.adapters.mongodb.clothingstore.persistence;

import es.upm.miw.apaw.adapters.mongodb.DatabaseSeeder;
import es.upm.miw.apaw.adapters.mongodb.clothingstore.daos.StoreRepository;
import es.upm.miw.apaw.adapters.mongodb.clothingstore.entities.StoreEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class StorePersistenceMongodbIT {

    @Autowired
    private StorePersistenceMongodb storePersistenceMongodb;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private DatabaseSeeder databaseSeeder;

    @BeforeEach
    void seed() {
        databaseSeeder.reSeedDatabase();
    }

    @Test
    void testDelete_ok() {
        StoreEntity any = storeRepository.findAll().stream().findFirst()
                .orElseThrow(() -> new IllegalStateException("no store seeded"));
        UUID id = any.getId();

        storePersistenceMongodb.delete(id);

        assertThat(storeRepository.findById(id)).isEmpty();
    }
}
