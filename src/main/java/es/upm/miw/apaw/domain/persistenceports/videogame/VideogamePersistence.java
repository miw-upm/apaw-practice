package es.upm.miw.apaw.domain.persistenceports.videogame;

import es.upm.miw.apaw.domain.models.videogame.Videogame;
import org.springframework.stereotype.Repository;

@Repository
public interface VideogamePersistence {

    void delete(String name);
}

