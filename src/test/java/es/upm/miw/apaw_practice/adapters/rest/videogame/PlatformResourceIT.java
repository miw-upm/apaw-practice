package es.upm.miw.apaw_practice.adapters.rest.videogame;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.videogame.Platform;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

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

    @Test
    void testUpdateConsoles() {
//        List<PlatformMemoryUpdating> platformMemoryUpdatingList = Arrays.asList(
//                new PlatformMemoryUpdating("playstation", "1tb")
//        );
//        this.webTestClient
//                .patch()
//                .uri(PlatformResource.PLATFORMS + PlatformResource.CONSOLE_NAME_ID, "playstation")
//                .body(BodyInserters.fromValue(platformMemoryUpdatingList))
//                .exchange()
//                .expectStatus().isOk();
    }

    @Test
    void testUpdateConsolesNotFound() {
//        List<PlatformMemoryUpdating> platformMemoryUpdatingList = Arrays.asList(
//                new PlatformMemoryUpdating("intellivision", "12kb")
//        );
//        this.webTestClient
//                .patch()
//                .uri(PlatformResource.PLATFORMS + PlatformResource.CONSOLE_NAME_ID, "intellivision")
//                .body(BodyInserters.fromValue(platformMemoryUpdatingList))
//                .exchange()
//                .expectStatus().isNotFound();
    }
}
