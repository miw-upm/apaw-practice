package es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.boardgame_cafe.Game;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class GamePersistenceMongodbIT {

    @Autowired
    private GamePersistenceMongodb gamePersistence;

    @Test
    void testReadNotFound() {
        assertThrows(NotFoundException.class, () -> gamePersistence.read("0"));
    }

    @Test
    void testGameNameNotExist() {
        assertFalse(gamePersistence.existGameName("0"));
    }

    @Test
    void testCreateAndRead() {
        Game game = new Game("Sushi GO", 8, "Party", 8);
        this.gamePersistence.create(game);
        Game gameBD = this.gamePersistence.read(game.getGameName());
        assertEquals("Sushi GO", gameBD.getGameName());
        assertEquals(8, gameBD.getNumPlayers());
        assertEquals("Party", gameBD.getGenre());
        assertEquals(8, gameBD.getNumberOfCopies());
    }

    @Test
    void testCreateAndUpdate() {
        Game game = new Game("Survive", 4, "Strategy", 2);
        this.gamePersistence.create(game);
        Game gameBD = this.gamePersistence.read(game.getGameName());
        assertEquals(game, gameBD);
        assertEquals("Survive", gameBD.getGameName());
        assertEquals(4, gameBD.getNumPlayers());
        assertEquals("Strategy", gameBD.getGenre());
        assertEquals(2, gameBD.getNumberOfCopies());
        game.setNumPlayers(6);
        game.setGenre("Family");
        game.setNumberOfCopies(1);
        this.gamePersistence.update(game.getGameName(), game);
        gameBD = this.gamePersistence.read(game.getGameName());
        assertEquals(6, gameBD.getNumPlayers());
        assertEquals("Family", gameBD.getGenre());
        assertEquals(1, gameBD.getNumberOfCopies());
    }

    @Test
    void testDelete() {
        Game game = new Game("Uno", 8, "Party", 10);
        this.gamePersistence.create(game);
        Game gameBD = this.gamePersistence.read(game.getGameName());
        assertEquals(game, gameBD);
        assertEquals("Uno", gameBD.getGameName());
        assertEquals(8, gameBD.getNumPlayers());
        assertEquals("Party", gameBD.getGenre());
        assertEquals(10, gameBD.getNumberOfCopies());
        this.gamePersistence.delete(game.getGameName());
        assertThrows(NotFoundException.class, () -> this.gamePersistence.read(game.getGameName()));
    }
}
