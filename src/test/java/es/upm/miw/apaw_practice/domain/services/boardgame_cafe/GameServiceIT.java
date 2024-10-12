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
    void readAllGames() {
        List<Game> games = gameService.readAllGames().toList();
        assertTrue(games.stream().anyMatch(game -> game.getGameName().equals("Azul")));
        assertTrue(games.stream().anyMatch(game -> game.getGameName().equals("Warewolf")));
    }

    @Test
    void deleteGame() {
        Game testGame = new Game("Scythe", 4, "Strategy", 2);
        gamePersistence.create(testGame);

        List<Game> games = gamePersistence.readAll().toList();
        int initialSize = games.size();
        assertTrue(games.stream().anyMatch(game -> game.getGameName().equals("Scythe")));
        gameService.deleteGame("Scythe");

        games = gamePersistence.readAll().toList();
        assertEquals(initialSize - 1, games.size());
        assertFalse(games.stream().anyMatch(game -> game.getGameName().equals("Scythe")));
    }

    @Test
    void testUpdateGameNumberOfCopies() {
        Game game = new Game("Pandemic", 6, "Strategy", 1);
        gamePersistence.create(game);
        Game gameBD = gameService.updateGameNumberOfCopies(game.getGameName(), 2);
        assertEquals(2, gameBD.getNumberOfCopies());

        gameBD = gamePersistence.read(game.getGameName());
        assertEquals(2, gameBD.getNumberOfCopies());
    }

}
