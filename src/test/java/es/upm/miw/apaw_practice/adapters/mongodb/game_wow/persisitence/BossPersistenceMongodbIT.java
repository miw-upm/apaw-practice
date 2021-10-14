package es.upm.miw.apaw_practice.adapters.mongodb.game_wow.persisitence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.game_wow.persistence.BossPersistenceMongodb;
import org.springframework.beans.factory.annotation.Autowired;

@TestConfig
public class BossPersistenceMongodbIT {

    @Autowired
    private BossPersistenceMongodb bossPersistenceMongodb;
}
