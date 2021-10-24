package es.upm.miw.apaw_practice.adapters.mongodb.videogame.persistence;

import es.upm.miw.apaw_practice.TestConfig;

import es.upm.miw.apaw_practice.adapters.mongodb.videogame.persistance.VideoGamePersistenceMongodb;
import es.upm.miw.apaw_practice.domain.models.videogame.Platform;
import es.upm.miw.apaw_practice.domain.models.videogame.VideoGame;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class VideoGamePersistenceMongodbIT {

    @Autowired
    public VideoGamePersistenceMongodb videoGamePersistenceMongodb;

    @Test
    void testReadAll() {
        Stream<VideoGame> videoGameStream = this.videoGamePersistenceMongodb.readAll();
        assertNotNull(videoGameStream);
    }

    @Test
    void testReadByTitle() {
        VideoGame videoGame = this.videoGamePersistenceMongodb.readByTitle("bayonetta 2");
        assertEquals(92, videoGame.getCritic().getExpertScore());
        assertEquals("bayonetta 2", videoGame.getTitle());
        assertEquals(8.5, videoGame.getCritic().getUserScore());
        assertTrue(videoGame.getCritic().getMustPlay());
        assertEquals(2, videoGame.getPlatforms().size());
        assertTrue(videoGame.getPlatforms().stream()
                .map(Platform::getConsoleName)
                .collect(Collectors.toList())
                .containsAll(List.of("switch", "wiiu")));
    }
}
