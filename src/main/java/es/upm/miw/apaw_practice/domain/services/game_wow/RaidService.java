package es.upm.miw.apaw_practice.domain.services.game_wow;

import es.upm.miw.apaw_practice.domain.models.game_wow.Raid;
import es.upm.miw.apaw_practice.domain.persistence_ports.game_wow.RaidPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RaidService {

    private final RaidPersistence raidPersistence;
    @Autowired
    public RaidService(RaidPersistence raidPersistence) {
        this.raidPersistence = raidPersistence;
    }

    public Raid updateDificultyRaid(String id, String dificulty){
        Raid raid = this.raidPersistence.readById(id);
        raid.setDificulty(dificulty);
        return this.raidPersistence.update(raid);
    }
}
