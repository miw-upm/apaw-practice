package es.upm.miw.apaw_practice.adapters.rest.videogame;

import es.upm.miw.apaw_practice.adapters.mongodb.videogame.VideoGameSeederService;
import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.videogame.Platform;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RestTestConfig
public class VideoGameCompanyResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private VideoGameSeederService videoGameSeederService;

    @Test
    void testUpdate() {
        List<Platform> platforms = Arrays.asList(
                new Platform("3ds", "xl", "32gb"),
                new Platform("ds", "xl", "512mb")
        );
        this.webTestClient
                .put()
                .uri(VideoGameCompanyResource.COMPANIES + VideoGameCompanyResource.NAME + VideoGameCompanyResource.PLATFORMS, "other")
                .body(BodyInserters.fromValue(platforms))
                .exchange()
                .expectStatus().isNotFound();
    }
}
