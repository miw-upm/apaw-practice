package es.upm.miw.apaw_practice.adapters.rest.videogame;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.videogame.Platform;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.math.BigDecimal;

@RestTestConfig
public class PlatformResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreate() {
        Platform platform =
                new Platform("wii", "RVL-001", "32gb");
        this.webTestClient
                .post()
                .uri(PlatformResource.PLATFORMS)
                .body(BodyInserters.fromValue(platform))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Platform.class)
                .value(Assertions::assertNotNull);
    }
}
