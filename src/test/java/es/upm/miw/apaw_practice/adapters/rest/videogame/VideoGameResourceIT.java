package es.upm.miw.apaw_practice.adapters.rest.videogame;

import es.upm.miw.apaw_practice.adapters.mongodb.videogame.VideoGameSeederService;
import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.videogame.VideoGame;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDate;

import static es.upm.miw.apaw_practice.adapters.rest.videogame.VideoGameResource.GAMES;
import static es.upm.miw.apaw_practice.adapters.rest.videogame.VideoGameResource.TITLE_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@RestTestConfig
public class VideoGameResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private VideoGameSeederService videoGameSeederService;

    @Test
    void testRead() {
        this.webTestClient
            .get()
            .uri(GAMES + TITLE_ID, "nba 2k21")
            .exchange()
            .expectStatus().isOk()
            .expectBody(VideoGame.class)
            .value(Assertions::assertNotNull)
            .value(videoGameData -> {
                assertEquals("nba 2k21", videoGameData.getTitle());
                assertEquals("e", videoGameData.getRating());
                assertEquals(LocalDate.of(2020, 9, 3), videoGameData.getReleaseDate());
                assertEquals(79, videoGameData.getCritic().getExpertScore());
                assertEquals(2.5, videoGameData.getCritic().getUserScore());
                assertFalse(videoGameData.getCritic().getMustPlay());
                assertEquals("switch", videoGameData.getPlatforms().get(0).getModel());
                assertEquals("64gb", videoGameData.getPlatforms().get(0).getMemory());
                assertEquals("xbox", videoGameData.getPlatforms().get(1).getModel());
                assertEquals("one s", videoGameData.getPlatforms().get(1).getConsoleName());
                assertEquals("playstation", videoGameData.getPlatforms().get(2).getModel());
            });
    }

    @Test
    void testReadNotFound() {
        this.webTestClient
                .get()
                .uri(GAMES + TITLE_ID, "other")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testDelete() {
        this.webTestClient
                .delete()
                .uri(GAMES + TITLE_ID, "ratchet & clank: rift apart")
                .exchange()
                .expectStatus().isOk();

        videoGameSeederService.deleteAll();
        videoGameSeederService.seedDatabase();
    }

}
