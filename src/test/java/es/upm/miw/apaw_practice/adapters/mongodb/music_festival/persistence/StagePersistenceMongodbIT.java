package es.upm.miw.apaw_practice.adapters.mongodb.music_festival.persistence;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
    void testCreate() {
        Stage stage = new Stage("PersistStage", "Loc", 2000, LocalDateTime.now());
        Stage stageSaved = this.stagePersistence.create(stage);
        assertEquals("PersistStage", stageSaved.getName());
        assertEquals("Loc", stageSaved.getLocation());
        assertEquals(2000, stage.getCapacity());
        assertNotNull(stage.getOpenTime());
    }

    @Test
    void testDelete() {
        String name = "TestStage2";
        assertDoesNotThrow(() -> this.stagePersistence.delete(name));
        assertThrows(NotFoundException.class,() -> this.stagePersistence.readByName(name));
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