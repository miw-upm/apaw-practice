package es.upm.miw.apaw_practice.adapters.rest.music_manager;

import es.upm.miw.apaw_practice.adapters.mongodb.music_manager.daos.SongRepository;
import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.*;

@RestTestConfig
class SongResourceIT {

    @Autowired
    private WebTestClient webTestClient;
    @Autowired
    private SongRepository songRepository;

    @Test
    void testDelete() {
        this.webTestClient
                .delete()
                .uri(SongResource.SONGS + "/Blackbird")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testFindSongTitlesByArtistFirstName() {
        this.webTestClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(SongResource.SONGS + SongResource.SONGTITLE + SongResource.SEARCH)
                        .queryParam("q", "firstName:Dave")
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(String.class);
    }
}
