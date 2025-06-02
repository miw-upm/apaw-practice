package es.upm.miw.apaw_practice.domain.services.music_festival;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.music_festival.Stage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@TestConfig
class StageServiceIT {
    @Autowired
    private StageService stageService;

    @Test
    void testDeleteWhenStageNotExists() {
        String name = "TestStageNotFound";
        assertDoesNotThrow(() -> this.stageService.delete(name));
        NotFoundException exception = assertThrows(NotFoundException.class, () -> this.stageService.read(name));
        assertEquals("Not Found Exception (404). Stage name:" + name, exception.getMessage());
    }

    @Test
    void testFindByName() {
        String name = "TestStage1";
        Stage stage = this.stageService.read(name);
        assertEquals(name, stage.getName());
    }
}
