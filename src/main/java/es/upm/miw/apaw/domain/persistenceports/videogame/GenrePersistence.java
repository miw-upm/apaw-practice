package es.upm.miw.apaw.domain.persistenceports.videogame;

import es.upm.miw.apaw.domain.models.videogame.Genre;
import org.springframework.stereotype.Repository;

@Repository
public interface GenrePersistence {
    Genre findByType(String type);
    Genre update(Genre genre);

}
