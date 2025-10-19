package es.upm.miw.apaw.functionaltests.music;

import es.upm.miw.apaw.adapters.mongodb.music.daos.MusicSeeder;
import es.upm.miw.apaw.adapters.resources.music.PlaylistResource;
import es.upm.miw.apaw.domain.models.music.Playlist;
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
class PlaylistResourceFT {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private MusicSeeder musicSeeder;

    @BeforeEach
    void setUp() {
        // igual que en shop: BD limpia y datos de prueba
        this.musicSeeder.deleteAll();
        this.musicSeeder.seedDatabase();
    }

    @Test
    void testDelete() {
        webTestClient.delete()
                .uri(PlaylistResource.PLAYLISTS + PlaylistResource.PLAYLIST_ID, "PL-001")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testUpdate() {
        Playlist playlist = Playlist.builder()
                .code("PL-001")            // tu modelo lo valida, inclúyelo
                .label("Electro Vibes Updated")
                .opened(true)
                .build();

        webTestClient.put()
                .uri(PlaylistResource.PLAYLISTS + PlaylistResource.PLAYLIST_ID, "PL-001")
                .bodyValue(playlist)
                .exchange()
                .expectStatus().isOk();     // ✅ sin body
    }

    @Test
    void testUpdateNotFound() {
        Playlist playlist = Playlist.builder()
                .code("PL-404")
                .label("Non Existing")
                .opened(false)
                .build();

        webTestClient.put()
                .uri(PlaylistResource.PLAYLISTS + PlaylistResource.PLAYLIST_ID, "PL-404")
                .bodyValue(playlist)
                .exchange()
                .expectStatus().isNotFound();
    }
}