package es.upm.miw.apaw_practice.adapters.mongodb.cinema.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.cinema.daos.FilmRepository;
import es.upm.miw.apaw_practice.domain.models.cinema.Film;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import es.upm.miw.apaw_practice.adapters.mongodb.cinema.CinemaSeederService;

import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class FilmPersistenceMongodbIT {
    @Autowired
    private FilmPersistenceMongodb filmPersistenceMongodb;
    private CinemaSeederService cinemaSeederService;

    @Autowired
    private FilmRepository filmRepository;
    @Test
    void testReadAll() {
       Stream<Film> films = this.filmPersistenceMongodb.readAll();
       assertNotNull(films);
    }

    @Test
    void testFindByScreenNumber() {
        Stream<Film> films = this.filmPersistenceMongodb.findByScreenNumber(2);
        assertEquals(films.count(), 2);
    }

    @Test
    void testDelete() {
        this.filmPersistenceMongodb.delete("7891");
        assertFalse(this.filmRepository.findAll().stream()
                .anyMatch(film -> "7891".equals(film.getBarcode()))
        );
    }
}
