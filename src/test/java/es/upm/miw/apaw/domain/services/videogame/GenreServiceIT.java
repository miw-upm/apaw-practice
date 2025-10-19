package es.upm.miw.apaw.domain.services.videogame;

import es.upm.miw.apaw.adapters.mongodb.videogame.persistence.GenrePersistenceMongoDB;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.videogame.Genre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
public class GenreServiceIT {

    @Autowired
    private GenreService genreService;

    @Autowired
    private GenrePersistenceMongoDB genrePersistenceMongoDB;


    @Test
    void testUpdateAgeRestriction() {

        Genre before = genrePersistenceMongoDB.findByType("action");
        assertThat(before.getAgeRestriction()).isEqualTo(10);

        Genre updated = genreService.updateAgeRestriction("action", 25);

        assertThat(updated.getAgeRestriction()).isEqualTo(25);

        Genre readAgain = genrePersistenceMongoDB.findByType("action");
        assertThat(readAgain.getAgeRestriction()).isEqualTo(25);
    }

    @Test
    void testUpdateAgeRestriction_nonExistingGenre() {
        // Verificamos que actualizar un género inexistente lanza NotFoundException
        assertThrows(NotFoundException.class,
                () -> genreService.updateAgeRestriction("nonexistent", 30));
    }
}
