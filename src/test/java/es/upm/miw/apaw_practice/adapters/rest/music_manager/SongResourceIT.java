package es.upm.miw.apaw_practice.adapters.rest.music_manager;

import es.upm.miw.apaw_practice.adapters.mongodb.music_manager.daos.SongRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.music_manager.entities.SongEntity;
import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.music_manager.Song;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

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
                .uri(SongResource.SONGS + "/Blackbird" )
                .exchange()
                .expectStatus().isOk();
    }
}