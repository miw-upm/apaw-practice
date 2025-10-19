package es.upm.miw.apaw.adapters.mongodb.videogame.persistence;

import es.upm.miw.apaw.adapters.mongodb.videogame.daos.VideogameSeeder;
import es.upm.miw.apaw.domain.models.videogame.LikeList;
import es.upm.miw.apaw.domain.models.videogame.Videogame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class LikeListPersistenceMongodbIT {

    @Autowired
    private LikeListPersistenceMongoDB likeListPersistenceMongoDB;

    @Autowired
    private VideogameSeeder videogameSeeder;


    @BeforeEach
    void setUp() {
        this.videogameSeeder.deleteAll();
        this.videogameSeeder.seedDatabase();
    }


    @Test
    void testReadSharedById() {
        assertTrue(this.likeListPersistenceMongoDB.readSharedById(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0020")));
    }

    @Test
    void testFindVideogamesByUserId_ReturnsCorrectGames() {

        UUID userId = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0002");

        List<Videogame> videogames = this.likeListPersistenceMongoDB.findVideogamesByUserId(userId)
                .toList();

        assertFalse(videogames.isEmpty());

        List<String> gameNames = videogames.stream()
                .map(Videogame::getName)
                .toList();

        assertTrue(gameNames.contains("game0"));
        assertTrue(gameNames.contains("game2"));
        assertEquals(2, gameNames.size());
    }
    @Test
    void testReadAll() {
        List<LikeList> likeLists = likeListPersistenceMongoDB.readAll().toList();

        assertThat(likeLists.size()).isEqualTo(3);
        assertThat(likeLists.get(0).getId()).isEqualTo(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0020"));
        assertThat(likeLists.get(1).getId()).isEqualTo(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0021"));
        assertThat(likeLists.get(2).getId()).isEqualTo(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0022"));

    }
}
