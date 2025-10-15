package es.upm.miw.apaw.domain.persistenceports.videogame;

import es.upm.miw.apaw.domain.models.videogame.Genre;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface GenrePersistence {
    Genre findByType(String type);
    Genre update(Genre genre);
    Stream<Genre> readAll();
}
