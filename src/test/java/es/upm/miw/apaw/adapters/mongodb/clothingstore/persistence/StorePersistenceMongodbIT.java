package es.upm.miw.apaw.adapters.mongodb.clothingstore.persistence;

import es.upm.miw.apaw.adapters.mongodb.DatabaseSeeder;
import es.upm.miw.apaw.adapters.mongodb.clothingstore.daos.StoreRepository;
import es.upm.miw.apaw.adapters.mongodb.clothingstore.daos.clothingstoreSeeder;
import es.upm.miw.apaw.adapters.mongodb.clothingstore.entities.StoreEntity;
import es.upm.miw.apaw.domain.models.clothingstore.Store;
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
    private static final UUID SEEDED_ID = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff7005");

    @Autowired
    private StorePersistenceMongodb storePersistenceMongodb;

    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private clothingstoreSeeder clothingstoreSeeder;


    @BeforeEach
    void resetDb() {
        clothingstoreSeeder.deleteAll();
        clothingstoreSeeder.seedDatabase();
    }

    @Test
    void testDelete_ok() {
        StoreEntity any = storeRepository.findAll().stream().findFirst()
                .orElseThrow(() -> new IllegalStateException("no store seeded"));
        UUID id = any.getId();

        storePersistenceMongodb.delete(id);

        assertThat(storeRepository.findById(id)).isEmpty();
    }
    @Test
    void testUpdate_PartialAddress_KeepOtherFields() {
        StoreEntity before = storeRepository.findById(SEEDED_ID).orElseThrow();
        String originalName = before.getName();

        Store partial = Store.builder().address("Calle Nueva 123").build();

        Store updated = storePersistenceMongodb.update(SEEDED_ID, partial);

        assertThat(updated.getId()).isEqualTo(SEEDED_ID);
        assertThat(updated.getAddress()).isEqualTo("Calle Nueva 123");
        assertThat(updated.getName()).isEqualTo(originalName);

        StoreEntity reloaded = storeRepository.findById(SEEDED_ID).orElseThrow();
        assertThat(reloaded.getAddress()).isEqualTo("Calle Nueva 123");
        assertThat(reloaded.getName()).isEqualTo(originalName);
    }
}
