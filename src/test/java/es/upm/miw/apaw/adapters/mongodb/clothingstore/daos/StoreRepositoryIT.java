package es.upm.miw.apaw.adapters.mongodb.clothingstore.daos;

import es.upm.miw.apaw.adapters.mongodb.DatabaseSeeder;
import es.upm.miw.apaw.adapters.mongodb.clothingstore.entities.StoreEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class StoreRepositoryIT {
    private static final UUID SEEDED_ID = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff7005");

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private DatabaseSeeder databaseSeeder;

    @BeforeEach
    void seed() {
        databaseSeeder.reSeedDatabase();
    }

    @Test
    void testFindAnyAndDelete() {
        StoreEntity any = storeRepository.findAll().stream().findFirst()
                .orElseThrow(() -> new IllegalStateException("no store seeded"));
        UUID id = any.getId();

        assertThat(storeRepository.findById(id)).isPresent();

        storeRepository.deleteById(id);

        assertThat(storeRepository.findById(id)).isEmpty();
    }
    @Test
    void testPatchLike_SaveOnlyAddress_KeepName() {
        StoreEntity before = storeRepository.findById(SEEDED_ID).orElseThrow();
        String originalName = before.getName();

        before.setAddress("Calle Nueva 123");
        storeRepository.save(before);

        Optional<StoreEntity> reloaded = storeRepository.findById(SEEDED_ID);
        assertThat(reloaded).isPresent();
        assertThat(reloaded.get().getAddress()).isEqualTo("Calle Nueva 123");
        assertThat(reloaded.get().getName()).isEqualTo(originalName); // 没改的字段保持不变
    }
}
