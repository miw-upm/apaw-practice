package es.upm.miw.apaw.functionaltests.music;

import es.upm.miw.apaw.adapters.resources.music.PlaylistResource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class PlaylistResourceFT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testDelete() {
        webTestClient.delete()
                .uri(PlaylistResource.PLAYLISTS + PlaylistResource.PLAYLIST_ID, "PL-001")
                .exchange()
                .expectStatus().isOk();
    }
}
