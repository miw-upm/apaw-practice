package es.upm.miw.apaw.domain.persistenceports.videogame;

import es.upm.miw.apaw.adapters.mongodb.videogame.entities.VideogameEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideogamePersistence {

    void delete(String name);

    List<VideogameEntity> findByGenre(String genreType);
    void updateOnlineByGenre(String genreType, boolean online);
}

