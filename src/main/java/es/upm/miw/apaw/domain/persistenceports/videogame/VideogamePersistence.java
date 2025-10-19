package es.upm.miw.apaw.domain.persistenceports.videogame;

import es.upm.miw.apaw.domain.models.videogame.Videogame;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideogamePersistence {

    void delete(String name);

    List<Videogame> findByGenre(String genreType);
    void updateOnlineByGenre(String genreType, boolean online);

}

