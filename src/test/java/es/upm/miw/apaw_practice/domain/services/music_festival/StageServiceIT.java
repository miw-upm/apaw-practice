package es.upm.miw.apaw_practice.domain.services.music_festival;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.ConflictException;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.music_festival.Stage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@TestConfig
class StageServiceIT {
    @Autowired
    private StageService stageService;

    @Test
    void testCreate() {
        Stage stage = new Stage("NewStage", "NewLocation", 5000, null);
        Stage created = this.stageService.create(stage);
        assertNotNull(created);
        assertEquals("NewStage", created.getName());
        assertEquals("NewLocation", created.getLocation());
        assertEquals( 5000, created.getCapacity());
        assertNull(created.getOpenTime());
    }

    @Test
    void testCreateConflict() {
        Stage stage = new Stage("MainStage", "Loc", 1, null);
        assertThrows(ConflictException.class, () -> this.stageService.create(stage));
    }

    @Test
    void testDeleteWhenStageNotExists() {
        String name = "TestStageNotFound";
        assertDoesNotThrow(() -> this.stageService.delete(name));
        NotFoundException exception = assertThrows(NotFoundException.class, () -> this.stageService.read(name));
        assertEquals("Not Found Exception (404). Stage name:" + name, exception.getMessage());
    }

    @Test
    void testFindByName() {
        String name = "MainStage";
        Stage stage = this.stageService.read(name);
        assertEquals(name, stage.getName());
    }
}
