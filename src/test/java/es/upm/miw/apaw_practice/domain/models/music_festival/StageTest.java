package es.upm.miw.apaw_practice.domain.models.music_festival;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class StageTest {

    @Test
    void testFull() {
        Stage stage = Stage.builder()
                .name("MainStage")
                .location("Central Park")
                .capacity(10000)
                .openTime(LocalDateTime.of(2025, 5, 15, 14, 0))
                .build();
        assertEquals("MainStage", stage.getName());
        assertEquals("Central Park", stage.getLocation());
        assertEquals(10000, stage.getCapacity());
        assertEquals(LocalDateTime.of(2025, 5, 15, 14, 0), stage.getOpenTime());
    }

    @Test
    void testPartial() {
        Stage stage = Stage.builder()
                .name("MainStage")
                .location("Central Park")
                .capacity(10000)
                .build();
        assertEquals("MainStage", stage.getName());
        assertEquals("Central Park", stage.getLocation());
        assertEquals(10000, stage.getCapacity());
        assertNull( stage.getOpenTime());
    }
}
