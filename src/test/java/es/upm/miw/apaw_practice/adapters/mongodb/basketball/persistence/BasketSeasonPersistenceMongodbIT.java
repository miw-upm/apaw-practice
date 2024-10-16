package es.upm.miw.apaw_practice.adapters.mongodb.basketball.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.basketball.BasketMatch;
import es.upm.miw.apaw_practice.domain.models.basketball.BasketSeason;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class BasketSeasonPersistenceMongodbIT {

    @Autowired
    private BasketSeasonPersistenceMongodb basketSeasonPersistenceMongodb;

    @Test
    void testCreateNoMatches() {
        BasketSeason basketSeason = new BasketSeason(4, 2022, 2023, "Serie A", emptyList());
        BasketSeason basketSeasonSaved = this.basketSeasonPersistenceMongodb.create(basketSeason);
        assertNotNull(basketSeasonSaved);
        assertEquals(2022, basketSeasonSaved.getStartYear());
        assertEquals(2023, basketSeasonSaved.getEndYear());
        assertEquals("Serie A", basketSeasonSaved.getLeague());
        assertNotNull(basketSeasonSaved.getBasketMatches());
        assertTrue(basketSeasonSaved.getBasketMatches().isEmpty());
    }

    @Test
    void testCreate() {
        BasketMatch basketMatch = new BasketMatch(1, LocalDateTime.of(2023, 10, 12, 18, 0), "Stadium A", emptyList());
        BasketSeason basketSeason = new BasketSeason(5, 2023, 2024, "Ligue Nationale", List.of(basketMatch));
        BasketSeason basketSeasonSaved = this.basketSeasonPersistenceMongodb.create(basketSeason);
        assertNotNull(basketSeasonSaved);
        assertEquals(2023, basketSeasonSaved.getStartYear());
        assertEquals(2024, basketSeasonSaved.getEndYear());
        assertEquals("Ligue Nationale", basketSeasonSaved.getLeague());
        assertNotNull(basketSeasonSaved.getBasketMatches());
        assertFalse(basketSeasonSaved.getBasketMatches().isEmpty());
    }
}
