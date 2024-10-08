package es.upm.miw.apaw_practice.adapters.rest.boardgame_cafe;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.boardgame_cafe.Game;
import es.upm.miw.apaw_practice.domain.models.boardgame_cafe.PlaySession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RestTestConfig
public class PlaySessionResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testUpdatePlaySessionGames() {
        Game game1 = new Game("CATAN", 4, "Strategy", 4);
        Game game2 = new Game("Zombicide", 6, "Co-op", 2);
        Game game3 = new Game("Azul", 4, "Family", 3);
        List<Game> selectedGames = List.of(game1, game2, game3);

        this.webTestClient
                .put()
                .uri(PlaySessionResource.PLAY_SESSION + PlaySessionResource.PLAYSESSIONID_ID + PlaySessionResource.GAMES, 0)
                .body(BodyInserters.fromValue(selectedGames))
                .exchange()
                .expectStatus().isOk()
                .expectBody(PlaySession.class)
                .value(playSession -> {
                    assertEquals(0, playSession.getPlaySessionId());
                    assertEquals(3, playSession.getSelectedGames().size());
                    assertEquals("CATAN", playSession.getSelectedGames().get(0).getGameName());
                    assertEquals("Zombicide", playSession.getSelectedGames().get(1).getGameName());
                    assertEquals("Azul", playSession.getSelectedGames().get(2).getGameName());
                });
    }
}
