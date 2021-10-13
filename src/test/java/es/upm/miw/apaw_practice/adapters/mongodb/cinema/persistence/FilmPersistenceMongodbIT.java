package es.upm.miw.apaw_practice.adapters.mongodb.cinema.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.cinema.entities.FilmEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.cinema.entities.ScreenEntity;
import es.upm.miw.apaw_practice.domain.models.cinema.Film;
import es.upm.miw.apaw_practice.domain.models.cinema.Screen;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class FilmPersistenceMongodbIT {
    @Autowired
    private FilmPersistenceMongodb filmPersistence;

    @Test
    void testFindFilmsByScreenNumber() {
        Screen screen = new Screen(1, 1, 70, false, null);
        Film film = new Film("7890", "The hunger games", "Katniss Everdeen voluntarily takes her younger sister's place in the Hunger Games",null ,screen);
        Stream<Film> filmDB = this.filmPersistence.findFilmsByScreenNumber(screen.getNumber());
        assertEquals(film, filmDB);
    }
}
