package es.upm.miw.apaw.functionaltests.videogame;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import static es.upm.miw.apaw.adapters.resources.shop.TagResource.NAME_ID;
import static es.upm.miw.apaw.adapters.resources.videogame.VideogameResource.VIDEOGAMES;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
public class VideogameResourceFT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testDelete(){

        webTestClient.delete()
                .uri(VIDEOGAMES + NAME_ID,"game0")
                .exchange()
                .expectStatus().isOk();

    }

}
