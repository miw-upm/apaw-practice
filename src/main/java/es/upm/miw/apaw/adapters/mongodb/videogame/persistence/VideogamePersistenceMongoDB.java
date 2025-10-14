package es.upm.miw.apaw.adapters.mongodb.videogame.persistence;

import es.upm.miw.apaw.adapters.mongodb.videogame.daos.VideogameRepository;
import es.upm.miw.apaw.domain.persistenceports.videogame.VideogamePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("videogameRepository")
public class VideogamePersistenceMongoDB implements VideogamePersistence {

    private final VideogameRepository videogameRepository;

    @Autowired
    public VideogamePersistenceMongoDB(VideogameRepository videogameRepository){
        this.videogameRepository =videogameRepository;

    }
    @Override
     public void delete(String name){
        this.videogameRepository
                .deleteByName(name);

    }


}
