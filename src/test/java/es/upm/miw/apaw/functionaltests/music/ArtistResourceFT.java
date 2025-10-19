package es.upm.miw.apaw.functionaltests.music;

import es.upm.miw.apaw.adapters.resources.music.ArtistResource;
import es.upm.miw.apaw.domain.models.music.Artist;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class ArtistResourceFT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testReadByNameOk() {
        webTestClient.get()
                .uri(ArtistResource.ARTISTS + ArtistResource.ARTIST_ID, "Tame Impala")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Artist.class)
                .value(artist -> {
                    assert artist.getName().equals("Tame Impala");
                    assert artist.getUser() != null;
                });
    }

    @Test
    void testReadByNameNotFound() {
        webTestClient.get()
                .uri(ArtistResource.ARTISTS + ArtistResource.ARTIST_ID, "Nonexistent Artist")
                .exchange()
                .expectStatus().isNotFound();
    }
}
