package es.upm.miw.apaw_practice.adapters.mongodb.cinema.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.cinema.entities.FilmEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
class FilmRepositoryIT {

    @Autowired
    private FilmRepository filmRepository;

    @Test
    void testFindFilmName() {
        List<FilmEntity> films = this.filmRepository.findAll();

        assertEquals(films.get(0).getName(), "The hunger games");
    }
}
