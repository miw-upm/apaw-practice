package es.upm.miw.apaw_practice.adapters.mongodb.basketball.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.basketball.BasketPlayer;
import es.upm.miw.apaw_practice.domain.persistence_ports.basketball.BasketPlayerPersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class BasketPlayerPersistenceMongodbIT {

    @Autowired
    private BasketPlayerPersistenceMongodb basketPlayerPersistenceMongodb;

    @Test
    void testFindByDniNotFound(){
        assertThrows(NotFoundException.class, () -> this.basketPlayerPersistenceMongodb.findByDni("00000000A"));
    }

    @Test
    void testFindByDni(){
        BasketPlayer basketPlayer = this.basketPlayerPersistenceMongodb.findByDni("12345678A");
        assertNotNull(basketPlayer);
        assertEquals("Lebron", basketPlayer.getName());
        assertEquals(7, basketPlayer.getDorsal());
        assertEquals(30, basketPlayer.getPoints());

    }
}
