package es.upm.miw.apaw_practice.domain.services.boardgame_cafe;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.boardgame_cafe.Game;
import es.upm.miw.apaw_practice.domain.persistence_ports.boardgame_cafe.GamePersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class GameServiceIT {

    @Autowired
    private GameService gameService;

    @Autowired
    private GamePersistence gamePersistence;

    @Test
    void deleteGame() {
        List<Game> games = gamePersistence.readAll().toList();
        int initialSize = games.size();
        assertEquals(initialSize, games.size());

        gameService.deleteGame(games.get(0).getGameName());

        assertEquals(initialSize - 1, gamePersistence.readAll().toList().size());
    }

    @Test
    void readAllGames() {
        List<Game> games = gameService.readAllGames().toList();
        assertEquals(8, games.size());
    }
}
