package es.upm.miw.apaw.adapters.mongodb.videogame.persistence;

import es.upm.miw.apaw.domain.models.videogame.Videogame;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
public class VideogamePersistenceMongodbIT {

    @Autowired
    private VideogamePersistenceMongoDB videogamePersistenceMongoDB;


    @Test
    void testUpdateOnlineByGenre() {
        String genreType = "rol";
        videogamePersistenceMongoDB.updateOnlineByGenre(genreType, false);
        List<Videogame> rolGames = videogamePersistenceMongoDB.findByGenre(genreType);
        assertThat(rolGames).extracting("online").containsOnly(false);

    }

    @Test
    void testFindByGenre_existingGenre() {
        String genreType = "rol";
        List<Videogame> games = videogamePersistenceMongoDB.findByGenre(genreType);

        assertThat(games.size()).isEqualTo(2);
        assertThat(games.get(0).getName()).isEqualTo("game1");
        assertThat(games.get(1).getName()).isEqualTo("game3");
    }

    @Test
    void testFindByGenre_nonExistingGenre() {
        String genreType = "nonexistent";

        // Debe lanzar RuntimeException
        assertThrows(RuntimeException.class, () -> {
            videogamePersistenceMongoDB.findByGenre(genreType);
        });
    }
}
