package es.upm.miw.apaw_practice.adapters.mongodb.game_wow.persisitence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.game_wow.persistence.BossPersistenceMongodb;
import es.upm.miw.apaw_practice.domain.models.game_wow.Boss;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class BossPersistenceMongodbIT {

    @Autowired
    private BossPersistenceMongodb bossPersistenceMongodb;

    @Test
    void testFindByEffort(){
        List<Boss> bosses = bossPersistenceMongodb.findByEffort("25N").collect(Collectors.toList());
        assertFalse(bosses.get(0).getDescription().equals("Archavon"));
        assertTrue(bosses.get(0).getEffort().equals("25N"));
        assertEquals("Plaguebringer's Stained Pants",bosses.get(0).getDropList().get(0).getTitle());
    }
}
