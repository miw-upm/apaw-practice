package es.upm.miw.apaw.domain.persistenceports.videogame;

import org.springframework.stereotype.Repository;

@Repository
public interface VideogamePersistence {

    void delete(String name);
}

