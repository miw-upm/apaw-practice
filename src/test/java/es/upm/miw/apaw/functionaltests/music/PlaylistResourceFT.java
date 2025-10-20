package es.upm.miw.apaw.functionaltests.music;

import es.upm.miw.apaw.adapters.mongodb.music.daos.MusicSeeder;
import es.upm.miw.apaw.adapters.resources.music.PlaylistResource;
import es.upm.miw.apaw.domain.models.music.Playlist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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

    @Test
    void testFindArtistNamesByLabelOk() throws Exception {
        String body = webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(PlaylistResource.PLAYLISTS + "/artist-names")
                        .queryParam("label", "Classics")
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .returnResult()
                .getResponseBody();

        com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();

        java.util.List<String> names;
        if (body != null && body.startsWith("[") && body.endsWith("]")) {
            names = mapper.readValue(body, new com.fasterxml.jackson.core.type.TypeReference<java.util.List<String>>() {});
        } else {
            String unwrapped = mapper.readValue(body, String.class);
            names = mapper.readValue(unwrapped, new com.fasterxml.jackson.core.type.TypeReference<java.util.List<String>>() {});
        }

        assertThat(names).isNotEmpty();
        assertThat(names).contains("Daft Punk", "Tame Impala");
        assertThat(names).doesNotHaveDuplicates();
    }

    @Test
    void testFindArtistNamesByLabelEmpty() {
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(PlaylistResource.PLAYLISTS + "/artist-names")
                        .queryParam("label", "NOPE")
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(String.class)
                .value(List::isEmpty);
    }
}