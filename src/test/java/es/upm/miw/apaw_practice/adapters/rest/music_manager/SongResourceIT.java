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

    private static final String TESTSONGID = "lithium";

    @BeforeEach
    void initSong() {
        SongEntity songEntity = new SongEntity(new Song("Lithium", "Grunge", 256));
        songEntity.setId(TESTSONGID);
        this.songRepository.save(songEntity);
    }

    @Test
    void testDelete() {
        this.webTestClient
                .delete()
                .uri(SongResource.SONGS + "/" + TESTSONGID )
                .exchange()
                .expectStatus().isOk();
    }
}