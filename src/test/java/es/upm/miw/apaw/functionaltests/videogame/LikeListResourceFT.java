package es.upm.miw.apaw.functionaltests.videogame;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.UUID;

import static es.upm.miw.apaw.adapters.resources.videogame.LikeListResource.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
public class LikeListResourceFT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testReadSharedById() {
        webTestClient.get()
                .uri(LIKE_LISTS+ID_ID+SHARED, UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0020"))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Boolean.class)
                .value(Assertions::assertTrue);
    }
}
