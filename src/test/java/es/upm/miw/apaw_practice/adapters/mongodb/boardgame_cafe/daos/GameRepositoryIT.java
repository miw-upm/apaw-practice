package es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe.entities.GameEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class GameRepositoryIT {

    @Autowired
    private GameRepository gameRepository;

    @Test
    void testFindByName() {
        Optional<GameEntity> gameEntity = this.gameRepository.findByGameName("CATAN");
        assertTrue(gameEntity.isPresent());
        assertNotNull(gameEntity.get());
        GameEntity game = gameEntity.get();
        assertEquals("CATAN", game.getGameName());
        assertEquals(4, game.getNumPlayers());
        assertEquals("Strategy", game.getGenre());
        assertEquals(4, game.getNumberOfCopies());
    }
}