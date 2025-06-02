package es.upm.miw.apaw_practice.adapters.mongodb.music_festival.persistence;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.music_festival.Stage;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@TestConfig
class StagePersistenceMongodbIT {

    @Autowired
    private StagePersistenceMongodb stagePersistence;

    @Test
    void testDelete() {
        String name = "TestStage2";
        assertDoesNotThrow(() -> this.stagePersistence.delete(name));
    }

    @Test
    void testDeleteNotFound() {
        String name = "TestStageNotFound";
        assertThrows(NotFoundException.class,() -> this.stagePersistence.delete(name));
    }

    @Test
    void testReadByName() {
        String name = "MainStage";
        Stage stage = this.stagePersistence.readByName(name);
        var stageDummy = new Stage(name, null, 0 , null);
        assertAll(
                () -> assertEquals(name, stage.getName()),
                () -> assertEquals("Central Park", stage.getLocation()),
                () -> assertEquals(10000, stage.getCapacity()),
                () -> assertTrue(stage.toString().contains(stageDummy.getName())),
                () -> assertEquals(LocalDateTime.of(2025, 5, 15, 14, 0), stage.getOpenTime())
        );
    }
    @Test
    void testReadByNameNotFound() {
        assertThrows(NotFoundException.class, () -> this.stagePersistence.readByName("Unknown"));
    }
}