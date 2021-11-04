package es.upm.miw.apaw_practice.adapters.rest.music_manager;

import es.upm.miw.apaw_practice.adapters.mongodb.music_manager.daos.ArtistRepository;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static org.junit.jupiter.api.Assertions.assertEquals;


@RestTestConfig
class ArtistResourceIT {

    @Autowired
    private WebTestClient webTestClient;
    @Autowired
    private ArtistRepository artistRepository;

    @Test
    void testUpdateAge() {
        Integer newAge = 52;
        this.webTestClient
                .put()
                .uri(ArtistResource.ARTISTS + "/Dave Grohl" + ArtistResource.AGE)
                .body(BodyInserters.fromValue(newAge))
                .exchange()
                .expectStatus().isOk();
    }

    //@Test
    void testSumAgeBySongGenre() {
        this.webTestClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(ArtistResource.ARTISTS + ArtistResource.AGE + ArtistResource.SUM)
                        .queryParam("q", "genre:Folk")
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(Integer.class)
                .value(sumAge -> assertEquals(258, sumAge));
    }
}