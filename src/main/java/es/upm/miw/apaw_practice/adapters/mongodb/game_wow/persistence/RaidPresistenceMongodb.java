package es.upm.miw.apaw_practice.adapters.mongodb.game_wow.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.game_wow.daos.RaidRepository;
import es.upm.miw.apaw_practice.domain.persistence_ports.game_wow.RaidPersistence;
import org.springframework.beans.factory.annotation.Autowired;

public class RaidPresistenceMongodb implements RaidPersistence {

    private RaidRepository raidRepository;

    @Autowired
    public RaidPresistenceMongodb(RaidRepository raidRepository) {
        this.raidRepository = raidRepository;
    }
}
