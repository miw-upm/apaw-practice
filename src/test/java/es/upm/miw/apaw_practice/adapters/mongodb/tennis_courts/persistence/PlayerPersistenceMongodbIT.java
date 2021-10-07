package es.upm.miw.apaw_practice.adapters.mongodb.tennis_courts.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.tennis_courts.Player;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class PlayerPersistenceMongodbIT {

    @Autowired
    private PlayerPersistenceMongoDB playerPersistence;

    @Test
    void testCreateAndRead(){
        this.playerPersistence.create(new Player("0L", "Sonia", "Garza", 25));
        assertEquals("Sonia", this.playerPersistence.read("0L").getName());
        assertThrows(NotFoundException.class, () -> this.playerPersistence.read("none"));
    }
}
