package es.upm.miw.apaw_practice.adapters.mongodb.basketball.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.basketball.entities.BasketSeasonEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class BasketSeasonRepositoryIT {

    @Autowired
    private BasketSeasonRepository basketSeasonRepository;

    @Test
    void testFindByLeague(){
        assertTrue(this.basketSeasonRepository.findByLeague("NBA").isPresent());
        BasketSeasonEntity basketSeasonEntity = this.basketSeasonRepository.findByLeague("NBA").get();
        assertEquals("NBA", basketSeasonEntity.getLeague());
        assertEquals(2022, basketSeasonEntity.getStartYear());
        assertEquals(2023, basketSeasonEntity.getEndYear());
        assertNotNull(basketSeasonEntity.getBasketMatchEntities());
    }
}
