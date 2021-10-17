package es.upm.miw.apaw_practice.domain.persistence_ports.cinema;

import es.upm.miw.apaw_practice.domain.models.cinema.Film;
import org.springframework.stereotype.Repository;
import java.util.stream.Stream;

@Repository
public interface FilmPersistence {

    Stream<Film> readAll();

    Stream<Film> findByScreenNumber(Integer number);

    void delete(String barcode);
}

