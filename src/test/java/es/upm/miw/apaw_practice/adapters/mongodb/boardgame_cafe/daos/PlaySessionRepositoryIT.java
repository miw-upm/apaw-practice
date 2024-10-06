package es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe.entities.GameEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe.entities.PlaySessionEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class PlaySessionRepositoryIT {

    @Autowired
    private PlaySessionRepository playSessionRepository;

    @Test
    void testFindByTableNumber() {
        Optional<PlaySessionEntity> playSessionEntity = this.playSessionRepository.findByPlaySessionId(0);
        assertTrue(playSessionEntity.isPresent());
        PlaySessionEntity playSession = playSessionEntity.get();
        assertEquals(0, playSession.getPlaySessionId());
        assertEquals(5, playSession.getGroupSize());
        assertEquals(LocalDateTime.of(2024, 9, 15, 13, 10), playSession.getSessionDate());
        List<GameEntity> selectedGames = playSession.getSelectedGamesEntities();
        assertEquals(2, selectedGames.size());
        assertEquals("CATAN", selectedGames.get(0).getGameName());
        assertEquals("Gloomhaven", selectedGames.get(1).getGameName());
    }
}