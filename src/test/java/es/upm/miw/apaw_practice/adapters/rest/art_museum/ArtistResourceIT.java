package es.upm.miw.apaw_practice.adapters.rest.art_museum;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.art_museum.Artist;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RestTestConfig
class ArtistResourceIT {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreate() {
        Artist artist = new Artist("Salvador Dali", 85, null);
        this.webTestClient
                .post()
                .uri(ArtistResource.ARTISTS)
                .body(BodyInserters.fromValue(artist))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Artist.class)
                .value(Assertions::assertNotNull);
    }

    @Test
    void testCreateConflict() {
        Artist artist = new Artist("Leonardo da Vinci", 85, null);
        this.webTestClient
                .post()
                .uri(ArtistResource.ARTISTS)
                .body(BodyInserters.fromValue(artist))
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void testSearchByExhibitionNameDistinctArtStyles() {
        this.webTestClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder.path(ArtistResource.ARTISTS + ArtistResource.SEARCH)
                                .queryParam("q", "exhibitionName:Spanish authors")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(List.class)
                .value(artStyles -> {
                    assertEquals(2, artStyles.size());
                    assertTrue(artStyles.contains("Baroque"));
                    assertTrue(artStyles.contains("Cubism"));
                });
    }
}
