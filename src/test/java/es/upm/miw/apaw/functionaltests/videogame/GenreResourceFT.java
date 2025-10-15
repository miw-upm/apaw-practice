package es.upm.miw.apaw.functionaltests.videogame;

import es.upm.miw.apaw.adapters.resources.videogame.GenreResource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
public class GenreResourceFT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void  testUpdateAgeRestriction(){
        webTestClient.put()
                .uri(uriBuilder -> uriBuilder
                        .path("/genres/ageRestriction")
                        .queryParam("type", "action")
                        .queryParam("newAge", 25)
                        .build())
                .exchange()
                .expectStatus().isOk(); // esperamos 200 OK

    }


}
