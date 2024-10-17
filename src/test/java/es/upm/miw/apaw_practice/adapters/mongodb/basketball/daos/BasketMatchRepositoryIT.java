package es.upm.miw.apaw_practice.adapters.mongodb.basketball.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.basketball.entities.BasketMatchEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class BasketMatchRepositoryIT {

    @Autowired
    private BasketMatchRepository basketMatchRepository;

    @Test
    void testFindByMatchId() {
        Optional<BasketMatchEntity> result = basketMatchRepository.findByMatchId(1);
        assertTrue(result.isPresent());
        BasketMatchEntity basketMatch = result.get();
        assertEquals(1, basketMatch.getMatchId());
        assertEquals("Stadium A", basketMatch.getAddress());
        assertEquals("Lebron", basketMatch.getBasketPlayers().get(0).getName());
    }

    @Test
    void testFindByIdList(){
        assertNotNull(this.basketMatchRepository.findByMatchIdIn(List.of(1,2)));
        List<BasketMatchEntity> basketMatchEntities = this.basketMatchRepository.findByMatchIdIn(List.of(1,2));
        assertEquals("Stadium A",basketMatchEntities.get(0).getAddress());
        assertEquals(LocalDateTime.of(2023, 10, 13, 19, 0),basketMatchEntities.get(1).getDate());
        assertNotNull(basketMatchEntities.get(0).getBasketPlayers());
        assertNotNull(basketMatchEntities.get(1).getBasketPlayers());
    }

    @Test
    void testDeleteByMatchId() {
        assertTrue(this.basketMatchRepository.deleteByMatchId(6).isPresent());
        assertFalse(this.basketMatchRepository.deleteByMatchId(0).isPresent());
    }
}
