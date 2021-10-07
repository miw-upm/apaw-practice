package es.upm.miw.apaw_practice.adapters.mongodb.game_wow.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.game_wow.daos.BossRepository;
import es.upm.miw.apaw_practice.domain.persistence_ports.game_wow.BossPersistence;
import org.springframework.beans.factory.annotation.Autowired;

public class BossPersistenceMongodb implements BossPersistence {

    private BossPersistence bossPersistence;

    @Autowired
    public BossPersistenceMongodb(BossPersistence bossPersistence) {
        this.bossPersistence = bossPersistence;
    }
}
