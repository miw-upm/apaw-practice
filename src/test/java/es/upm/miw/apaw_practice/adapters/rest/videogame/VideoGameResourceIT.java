package es.upm.miw.apaw_practice.adapters.rest.videogame;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.videogame.VideoGame;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDate;

import static es.upm.miw.apaw_practice.adapters.rest.videogame.VideoGameResource.GAMES;
import static es.upm.miw.apaw_practice.adapters.rest.videogame.VideoGameResource.TITLE;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RestTestConfig
public class VideoGameResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testRead() {
        this.webTestClient
            .get()
            .uri(GAMES + TITLE, "nba 2k21")
            .exchange()
            .expectStatus().isOk()
            .expectBody(VideoGame.class)
            .value(Assertions::assertNotNull)
            .value(videoGameData -> {
                assertEquals("nba 2k21", videoGameData.getTitle());
                assertEquals("e", videoGameData.getRating());
                assertEquals(LocalDate.of(2020, 9, 3), videoGameData.getReleaseDate());
                assertEquals("switch", videoGameData.getPlatforms().get(0).getConsoleName());
                assertEquals("xbox", videoGameData.getPlatforms().get(1).getConsoleName());
                assertEquals("playstation", videoGameData.getPlatforms().get(2).getConsoleName());
            });
    }

    @Test
    void testReadNotFound() {
        this.webTestClient
                .get()
                .uri(GAMES + TITLE, "kk")
                .exchange()
                .expectStatus().isNotFound();
    }

}
