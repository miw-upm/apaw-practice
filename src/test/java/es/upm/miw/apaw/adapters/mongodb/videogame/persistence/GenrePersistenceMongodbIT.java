package es.upm.miw.apaw.adapters.mongodb.videogame.persistence;

import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.videogame.Genre;
import es.upm.miw.apaw.domain.persistenceports.videogame.GenrePersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
public class GenrePersistenceMongodbIT {

    @Autowired
    private GenrePersistenceMongoDB genrePersistenceMongoDB;

    @Test
    void testUpdate(){

        Genre genre = genrePersistenceMongoDB.findByType("action");
        assertThat(genre).isNotNull();

        genre.setAgeRestriction(21);
        Genre updated = genrePersistenceMongoDB.update(genre);

        assertThat(updated.getAgeRestriction()).isEqualTo(21);


        Genre readAgain = genrePersistenceMongoDB.readAll()
                .filter(g -> "action".equals(g.getType()))
                .findFirst()
                .orElseThrow();
        assertThat(readAgain.getAgeRestriction()).isEqualTo(21);
    }
    @Test
    void testUpdate_nonExistingGenre() {
        Genre fakeGenre = new Genre();
        fakeGenre.setType("nonexistent");
        fakeGenre.setAgeRestriction(18);

        assertThrows(NotFoundException.class,
                () -> genrePersistenceMongoDB.update(fakeGenre));
    }
}
