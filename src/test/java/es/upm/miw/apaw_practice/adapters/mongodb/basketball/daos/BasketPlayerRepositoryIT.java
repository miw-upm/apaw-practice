package es.upm.miw.apaw_practice.adapters.mongodb.basketball.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.basketball.entities.BasketPlayerEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class BasketPlayerRepositoryIT {

    @Autowired
    private BasketPlayerRepository basketPlayerRepository;

    @Test
    void testFindByDni(){
        assertTrue(this.basketPlayerRepository.findByDni("12345678A").isPresent());
        BasketPlayerEntity basketPlayer = this.basketPlayerRepository.findByDni("12345678A").get();
        assertEquals("Lebron", basketPlayer.getName());
        assertEquals(7, basketPlayer.getDorsal());
        assertEquals(30 , basketPlayer.getPoints());
    }
}
