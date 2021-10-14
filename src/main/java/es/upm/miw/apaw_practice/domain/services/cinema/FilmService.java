package es.upm.miw.apaw_practice.domain.services.cinema;

import es.upm.miw.apaw_practice.domain.models.cinema.Film;
import es.upm.miw.apaw_practice.domain.models.cinema.Screen;
import es.upm.miw.apaw_practice.domain.persistence_ports.cinema.FilmPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.stream.Stream;

@Service
public class FilmService {
    private final FilmPersistence filmPersistence;

    @Autowired
    public FilmService(FilmPersistence filmPersistence) {
        this.filmPersistence = filmPersistence;
    }

    private Integer selectScreenNumber(Screen screen){
        return screen.getNumber();
    }

    public Stream<Film> findFilmsByScreenNumber(Integer number){
        return this.filmPersistence.readAll()
                .filter(film -> number.equals(film.getScreen().getNumber()));
    }
}
