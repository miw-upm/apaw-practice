package es.upm.miw.apaw_practice.adapters.mongodb.football.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class FootballPlayerPersistenceMongodbIT {

    @Autowired
    private FootballPlayerPersistenceMongodb footballPlayerPersistenceMongodb;

    @Test
    void testReadAll() {
        assertEquals(6, footballPlayerPersistenceMongodb.readAll().count());

        assertTrue(this.footballPlayerPersistenceMongodb.readAll()
                .anyMatch(player ->
                        24 == player.getAge() &&
                                10 == player.getGoalsScored() &&
                                Boolean.TRUE == player.isDefense()
                ));
    }

}
