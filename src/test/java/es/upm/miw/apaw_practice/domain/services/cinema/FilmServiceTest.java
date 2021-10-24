package es.upm.miw.apaw_practice.domain.services.cinema;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.cinema.Film;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
class FilmServiceTest {
    @Autowired
    private FilmService filmService;

    @Test
    void testFindFilmsByScreenNumber() {
        Stream<Film> films = filmService.findFilmsByScreenNumber(2);
        assertEquals("Optional[The hunger games]", films.findFirst().map(Film::getName).toString());
    }
}
