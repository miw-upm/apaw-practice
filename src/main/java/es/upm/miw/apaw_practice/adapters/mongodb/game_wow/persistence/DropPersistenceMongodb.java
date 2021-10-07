package es.upm.miw.apaw_practice.adapters.mongodb.game_wow.persistence;

import es.upm.miw.apaw_practice.domain.persistence_ports.game_wow.DropPersistence;
import org.springframework.beans.factory.annotation.Autowired;

public class DropPersistenceMongodb implements DropPersistence {

    private DropPersistence dropPersistence;

    @Autowired
    public DropPersistenceMongodb(DropPersistence dropPersistence) {
        this.dropPersistence = dropPersistence;
    }
}
