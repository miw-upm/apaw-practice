package es.upm.miw.apaw_practice.adapters.mongodb.videogame.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.videogame.entities.VideoGameEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

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
        assertEquals(8.5, videoGameEntity.getCritic().getUserScore());
        assertEquals("switch", videoGameEntity.getPlatforms().get(0).getConsoleName());
    }
}
