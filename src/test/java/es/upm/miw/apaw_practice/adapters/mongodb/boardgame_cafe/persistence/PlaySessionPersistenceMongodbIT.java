package es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe.daos.GameRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe.entities.GameEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.boardgame_cafe.Game;
import es.upm.miw.apaw_practice.domain.models.boardgame_cafe.PlaySession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class PlaySessionPersistenceMongodbIT {

    @Autowired
    private PlaySessionPersistenceMongodb playSessionPersistence;

    @Autowired
    private GameRepository gameRepository;

    @Test
    void testReadNotFound() {
        assertThrows(NotFoundException.class, () -> this.playSessionPersistence.read(9999));
    }

    @Test
    void testPlaySessionIdNotExist() {
        assertFalse(this.playSessionPersistence.existPlaySessionId(9999));
    }

    @Test
    void testPlaySessionIdExist() {
        assertTrue(this.playSessionPersistence.existPlaySessionId(0));
    }

    @Test
    void testCreateAndRead() {
        GameEntity game1 = gameRepository.findByGameName("CATAN").orElseThrow(() -> new NotFoundException("Game not found: CATAN"));
        GameEntity game2 = gameRepository.findByGameName("Gloomhaven").orElseThrow(() -> new NotFoundException("Game not found: Gloomhaven"));
        List<Game> selectedGames = List.of(game1.toGame(), game2.toGame());
        PlaySession playSession = new PlaySession(5, 10, LocalDateTime.of(2024, 10, 6, 12, 0), selectedGames);
        this.playSessionPersistence.create(playSession);
        PlaySession playSessionBD = this.playSessionPersistence.read(playSession.getPlaySessionId());
        assertEquals(5, playSessionBD.getPlaySessionId());
        assertEquals(10, playSessionBD.getGroupSize());
        assertEquals(LocalDateTime.of(2024, 10, 6, 12, 0), playSessionBD.getSessionDate());
        assertEquals(2, playSessionBD.getSelectedGames().size());
        assertEquals("CATAN", playSessionBD.getSelectedGames().get(0).getGameName());
        assertEquals("Gloomhaven", playSessionBD.getSelectedGames().get(1).getGameName());
    }

    @Test
    void testCreateAndUpdate() {
        GameEntity game1 = gameRepository.findByGameName("CATAN").orElseThrow(() -> new NotFoundException("Game not found: CATAN"));
        GameEntity game2 = gameRepository.findByGameName("Gloomhaven").orElseThrow(() -> new NotFoundException("Game not found: Gloomhaven"));
        List<Game> selectedGames = List.of(game1.toGame(), game2.toGame());
        PlaySession playSession = new PlaySession(5, 10, LocalDateTime.of(2024, 10, 6, 12, 0), selectedGames);
        this.playSessionPersistence.create(playSession);
        PlaySession playSessionBD = this.playSessionPersistence.read(playSession.getPlaySessionId());
        assertEquals(playSession, playSessionBD);
        assertEquals(5, playSessionBD.getPlaySessionId());
        assertEquals(10, playSessionBD.getGroupSize());
        assertEquals(LocalDateTime.of(2024, 10, 6, 12, 0), playSessionBD.getSessionDate());
        assertEquals(2, playSessionBD.getSelectedGames().size());
        assertEquals("CATAN", playSessionBD.getSelectedGames().get(0).getGameName());
        assertEquals("Gloomhaven", playSessionBD.getSelectedGames().get(1).getGameName());
        playSession.setGroupSize(8);
        playSession.setSessionDate(LocalDateTime.of(2024, 10, 6, 14, 0));
        playSession.setSelectedGames(List.of(game1.toGame()));
        this.playSessionPersistence.update(playSession.getPlaySessionId(), playSession);
        playSessionBD = this.playSessionPersistence.read(playSession.getPlaySessionId());
        assertEquals(8, playSessionBD.getGroupSize());
        assertEquals(LocalDateTime.of(2024, 10, 6, 14, 0), playSessionBD.getSessionDate());
        assertEquals(1, playSessionBD.getSelectedGames().size());
        assertEquals("CATAN", playSessionBD.getSelectedGames().get(0).getGameName());
    }
}