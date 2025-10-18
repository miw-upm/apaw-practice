package es.upm.miw.apaw.adapters.mongodb.videogame.persistence;

import es.upm.miw.apaw.domain.models.videogame.Videogame;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class LikeListPersistenceMongodbIT {

    @Autowired
    private LikeListPersistenceMongoDB likeListPersistenceMongoDB;

    @Test
    void testReadSharedById() {
        assertTrue(this.likeListPersistenceMongoDB.readSharedById(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0020")));
    }

    @Test
    void testFindVideogamesByUserId_ReturnsCorrectGames() {

        UUID userId = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0003");

        List<Videogame> videogames = this.likeListPersistenceMongoDB.findVideogamesByUserId(userId)
                .toList();

        assertFalse(videogames.isEmpty());

        List<String> gameNames = videogames.stream()
                .map(Videogame::getName)
                .toList();

        assertTrue(gameNames.contains("game0"));
        assertTrue(gameNames.contains("game1"));
        assertEquals(2, gameNames.size());
    }
}
