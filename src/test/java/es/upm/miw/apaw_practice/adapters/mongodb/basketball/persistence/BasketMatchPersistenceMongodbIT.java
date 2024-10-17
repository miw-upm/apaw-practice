package es.upm.miw.apaw_practice.adapters.mongodb.basketball.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.basketball.BasketMatch;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class BasketMatchPersistenceMongodbIT {

    @Autowired
    private BasketMatchPersistenceMongodb basketMatchPersistenceMongodb;

    @Test
    void testUpdateAddressNotFound(){
        assertThrows(NotFoundException.class, () -> this.basketMatchPersistenceMongodb.updateAddress(0,"Parque de Vallecas"));
    }

    @Test
    void testUpdateAddress(){
        BasketMatch basketMatch = this.basketMatchPersistenceMongodb.updateAddress(3,"Wizink Center");
        assertNotNull(basketMatch);
        assertEquals("Wizink Center", basketMatch.getAddress());
        assertNotNull(basketMatch.getDate());
        assertNotNull(basketMatch.getPlayers());
    }
}
