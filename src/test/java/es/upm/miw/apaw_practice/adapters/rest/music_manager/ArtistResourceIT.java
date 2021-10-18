package es.upm.miw.apaw_practice.adapters.rest.music_manager;

import es.upm.miw.apaw_practice.adapters.mongodb.music_manager.daos.ArtistRepository;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;


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
}