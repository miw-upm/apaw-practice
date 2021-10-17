package es.upm.miw.apaw_practice.adapters.mongodb.game_wow.persisitence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.game_wow.persistence.RaidPersistenceMongodb;
import es.upm.miw.apaw_practice.domain.models.game_wow.Boss;
import es.upm.miw.apaw_practice.domain.models.game_wow.Drop;
import es.upm.miw.apaw_practice.domain.models.game_wow.Feature;
import es.upm.miw.apaw_practice.domain.models.game_wow.Raid;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class RaidPersistenceMongodbIT {

    @Autowired
    private RaidPersistenceMongodb raidPersistenceMongodb;

    @Test
    void testUpdate () {
        Date raidDate = new Date();
        Feature feature = new Feature("Legs", 171, 200, 100, "Use: Restores 1625 mana");
        Drop drop = new Drop("Plaguebringer's Stained Pants", "mage,priest,warlock", 264, feature);
        Boss boss = new Boss("Festergut", "25N", List.of(drop));
        Raid raidCreation = new Raid(raidDate, "ICC", "25N", 25, false, List.of(boss));

        Raid raidBD = this.raidPersistenceMongodb.create(raidCreation);
        raidBD.setDificulty("25H");
        this.raidPersistenceMongodb.update(raidBD);
        raidBD = this.raidPersistenceMongodb.readById(raidBD.getId());
        assertEquals("25H", raidBD.getDificulty());
    }
}
