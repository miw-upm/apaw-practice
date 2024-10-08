package es.upm.miw.apaw_practice.domain.services.boardgame_cafe;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.boardgame_cafe.Game;
import es.upm.miw.apaw_practice.domain.models.boardgame_cafe.PlaySession;
import es.upm.miw.apaw_practice.domain.persistence_ports.boardgame_cafe.PlaySessionPersistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@TestConfig
class PlaySessionServiceIT {

    @Autowired
    private PlaySessionService playSessionService;

    @MockBean
    private PlaySessionPersistence playSessionPersistence;

    private PlaySession playSession;

    @BeforeEach
    void setUp() {
        playSession = new PlaySession();
        playSession.setPlaySessionId(0);
    }

    @Test
    void testUpdatePlaySessionGames() {
        Game game1 = new Game("CATAN", 4, "Strategy", 4);
        Game game2 = new Game("Zombicide", 6, "Co-op", 2);
        Game game3 = new Game("Azul", 4, "Family", 3);
        List<Game> selectedGames = List.of(game1, game2, game3);

        when(playSessionPersistence.read(0)).thenReturn(playSession);
        when(playSessionPersistence.update(eq(0), any(PlaySession.class))).thenReturn(playSession);

        PlaySession updatedPlaySession = playSessionService.updatePlaySessionGames(0, selectedGames);

        assertEquals(0, updatedPlaySession.getPlaySessionId());
        assertEquals(3, updatedPlaySession.getSelectedGames().size());
        assertEquals("CATAN", updatedPlaySession.getSelectedGames().get(0).getGameName());
        assertEquals("Zombicide", updatedPlaySession.getSelectedGames().get(1).getGameName());
        assertEquals("Azul", updatedPlaySession.getSelectedGames().get(2).getGameName());
    }
}