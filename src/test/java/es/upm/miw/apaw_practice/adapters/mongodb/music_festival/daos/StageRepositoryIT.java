package es.upm.miw.apaw_practice.adapters.mongodb.music_festival.daos;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.music_festival.entities.StageEntity;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@TestConfig
class StageRepositoryIT {
    @Autowired
    private StageRepository stageRepository;

    @Test
    void testFindByName() {
        Optional<StageEntity> stageEntityOptional = this.stageRepository.findByName("MainStage");
        assertTrue(stageEntityOptional.isPresent());
        StageEntity stageEntity = stageEntityOptional.get();
        assertAll(
                () -> assertNotNull(stageEntity.getId()),
                () -> assertEquals("MainStage", stageEntity.getName()),
                () -> assertEquals("Central Park", stageEntity.getLocation()),
                () -> assertEquals(10000, stageEntity.getCapacity()),
                () -> assertEquals(LocalDateTime.of(2025, 5, 15, 14, 0), stageEntity.getOpenTime()),
                () -> assertEquals(stageEntity.hashCode(), Objects.hashCode(stageEntity.getName())),
                () -> assertTrue(stageEntity.toString().contains(stageEntity.getName()))
        );
    }

    @Test
    void testFindByNameNotFound() {
        assertTrue(this.stageRepository.findByName("Unknown").isEmpty());
    }

}
