package es.upm.miw.apaw_practice.domain.services.music_festival;

import static org.junit.jupiter.api.Assertions.assertThrows;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@TestConfig
class StageServiceIT {
    @Autowired
    private StageService stageService;

    @Test
    void testDeleteNotFound() {
        assertThrows(NotFoundException.class, () -> this.stageService.delete("TestStageNotFound"));
    }
}
