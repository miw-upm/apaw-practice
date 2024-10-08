package es.upm.miw.apaw_practice.adapters.rest.boardgame_cafe;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.boardgame_cafe.Game;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.*;

@RestTestConfig
public class GameResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testNumberOfGames() {
        this.webTestClient
                .get()
                .uri(GameResource.GAME)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Game.class)
                .value(games -> assertEquals(6, games.size()));
    }

    @Test
    void testDeleteGame() {
        this.webTestClient
                .delete()
                .uri(GameResource.GAME + GameResource.GAMENAME_ID, "CATAN")
                .exchange()
                .expectStatus().isOk();

        this.webTestClient
                .get()
                .uri(GameResource.GAME)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Game.class)
                .value(games -> assertEquals(5, games.size()));
    }
}