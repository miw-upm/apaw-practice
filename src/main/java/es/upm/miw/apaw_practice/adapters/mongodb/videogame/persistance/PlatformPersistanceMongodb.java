package es.upm.miw.apaw_practice.adapters.mongodb.videogame.persistance;

import es.upm.miw.apaw_practice.adapters.mongodb.videogame.daos.PlatformRepository;
import es.upm.miw.apaw_practice.domain.persistence_ports.videogame.PlatformPersistance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("platformPersistance")
public class PlatformPersistanceMongodb implements PlatformPersistance {

    private final PlatformRepository platformRepository;

    @Autowired
    public PlatformPersistanceMongodb(PlatformRepository platformRepository) {
        this.platformRepository = platformRepository;
    }
}
