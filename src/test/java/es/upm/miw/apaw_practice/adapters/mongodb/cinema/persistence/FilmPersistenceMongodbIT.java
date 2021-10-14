package es.upm.miw.apaw_practice.adapters.mongodb.cinema.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.cinema.entities.FilmEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.cinema.entities.ScreenEntity;
import es.upm.miw.apaw_practice.domain.models.cinema.Film;
import es.upm.miw.apaw_practice.domain.models.cinema.Screen;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import es.upm.miw.apaw_practice.adapters.mongodb.cinema.CinemaSeederService;
import java.util.Optional;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class FilmPersistenceMongodbIT {
    @Autowired
    private FilmPersistenceMongodb filmPersistenceMongodb;
    private CinemaSeederService cinemaSeederService;

    @Test
    void testReadAll() {
       Stream<Film> films = this.filmPersistenceMongodb.readAll();
       assertNotNull(films);
    }
}
