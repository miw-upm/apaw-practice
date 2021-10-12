package es.upm.miw.apaw_practice.adapters.mongodb.football.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
class MatchPersistenceMongodbIT {

    @Autowired
    private MatchPersistenceMongodb matchPersistenceMongodb;

    @Test
    void testDelete() {
        assertEquals(this.matchPersistenceMongodb.delete(6), 1);
        assertEquals(this.matchPersistenceMongodb.delete(8), 0);
    }
}
