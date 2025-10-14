package es.upm.miw.apaw.domain.services.videogame;

import es.upm.miw.apaw.adapters.mongodb.videogame.daos.VideogameSeeder;
import es.upm.miw.apaw.domain.persistenceports.videogame.VideogamePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideogameService {

    private final VideogamePersistence videogamePersistence;

    @Autowired
    public VideogameService(VideogamePersistence videogamePersistence) {
        this.videogamePersistence = videogamePersistence;
    }

    public void delete(String name){
        this.videogamePersistence.delete(name);
    }
}
