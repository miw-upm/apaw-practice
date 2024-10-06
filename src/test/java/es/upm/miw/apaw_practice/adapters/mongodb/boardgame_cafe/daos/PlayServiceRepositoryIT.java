package es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe.entities.GameEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe.entities.PlayServiceEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class PlayServiceRepositoryIT {

    @Autowired
    private PlayServiceRepository playServiceRepository;

    @Test
    void testFindByTableNumber() {
        Optional<PlayServiceEntity> playServiceEntity = this.playServiceRepository.findByPlayServiceId(0);
        assertTrue(playServiceEntity.isPresent());
        PlayServiceEntity playService = playServiceEntity.get();
        assertEquals(0, playService.getPlayServiceId());
        assertEquals(5, playService.getGroupSize());
        assertEquals(LocalDateTime.of(2024, 9, 15, 13, 10), playService.getSessionDate());
        List<GameEntity> selectedGames = playService.getSelectedGamesEntities();
        assertEquals(2, selectedGames.size());
        assertEquals("CATAN", selectedGames.get(0).getGameName());
        assertEquals("Gloomhaven", selectedGames.get(1).getGameName());
    }
}