package es.upm.miw.apaw.adapters.mongodb.videogame.persistence;

import es.upm.miw.apaw.adapters.mongodb.videogame.daos.GenreRepository;
import es.upm.miw.apaw.adapters.mongodb.videogame.daos.VideogameRepository;
import es.upm.miw.apaw.adapters.mongodb.videogame.entities.GenreEntity;
import es.upm.miw.apaw.adapters.mongodb.videogame.entities.VideogameEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class VideogamePersistenceMongodbIT {

    @Autowired
    private VideogamePersistenceMongoDB videogamePersistence;

    @Autowired
    private VideogameRepository videogameRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Test
    void testUpdateOnlineByGenre() {
        VideogamePersistenceMongoDB persistence = new VideogamePersistenceMongoDB(videogameRepository, genreRepository);

        GenreEntity genre = new GenreEntity();
        genre.setType("TestGenre");
        genreRepository.save(genre);

        VideogameEntity v1 = new VideogameEntity();
        v1.setName("Videogame1");
        v1.setOnline(true);
        v1.setGenreEntity(genre);

        VideogameEntity v2 = new VideogameEntity();
        v2.setName("Videogame2");
        v2.setOnline(true);
        v2.setGenreEntity(genre);

        persistence.saveAll(List.of(v1, v2));

        persistence.updateOnlineByGenre("TestGenre", false);

        List<VideogameEntity> updated = persistence.findByGenre("TestGenre");
        assertThat(updated).allMatch(v -> !v.getOnline());
    }
}
