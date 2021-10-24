package es.upm.miw.apaw_practice.adapters.mongodb.videogame.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.videogame.entities.VideoGameEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class VideoGameRepositoryIT {

    @Autowired
    private VideoGameRepository videoGameRepository;

    @Test
    void testFindByTitle() {
        assertTrue(this.videoGameRepository.findByTitle("bayonetta 2").isPresent());
        VideoGameEntity videoGameEntity = this.videoGameRepository.findByTitle("bayonetta 2").get();
        assertEquals(8.5, videoGameEntity.getCriticEntity().getUserScore());
        assertEquals(92, videoGameEntity.getCriticEntity().getExpertScore());
        assertTrue(videoGameEntity.getCriticEntity().getMustPlay());
        assertEquals(LocalDate.of(2014, 9, 20), videoGameEntity.getReleaseDate());
        assertEquals("wiiu", videoGameEntity.getPlatformEntities().get(1).getModel());
        assertEquals("8gb", videoGameEntity.getPlatformEntities().get(1).getMemory());
        assertEquals("deluxe", videoGameEntity.getPlatformEntities().get(1).getConsoleName());
    }
}
