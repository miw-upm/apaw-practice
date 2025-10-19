package es.upm.miw.apaw.functionaltests.music;

import es.upm.miw.apaw.adapters.mongodb.music.daos.MusicSeeder;
import es.upm.miw.apaw.adapters.resources.music.SongResource;
import es.upm.miw.apaw.domain.models.music.Song;
import es.upm.miw.apaw.domain.models.music.Style;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class SongResourceFT {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private MusicSeeder musicSeeder;

    @BeforeEach
    void setUp() {
        this.musicSeeder.deleteAll();
        this.musicSeeder.seedDatabase();
    }

    @Test
    void testCreateOk() {
        String isrc = "ESABC0000001";

        Song body = Song.builder()
                .isrc(isrc)
                .title("FT New Song")
                .durationSeconds(215)
                .style(Style.builder()
                        .genre("ELECTRO")
                        .popularityIndex(70)
                        .mood("ENERGETIC")
                        .build())
                .build();

        this.webTestClient.post()
                .uri(SongResource.SONGS)
                .bodyValue(body)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testCreateConflict() {
        String isrc = "ESABC0000002";

        Song body = Song.builder()
                .isrc(isrc)
                .title("FT Duplicate")
                .durationSeconds(215)
                .style(Style.builder()
                        .genre("ELECTRO")
                        .popularityIndex(70)
                        .mood("ENERGETIC")
                        .build())
                .build();

        this.webTestClient.post()
                .uri(SongResource.SONGS)
                .bodyValue(body)
                .exchange()
                .expectStatus().isOk();

        this.webTestClient.post()
                .uri(SongResource.SONGS)
                .bodyValue(body)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }
}
