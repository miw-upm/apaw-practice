package es.upm.miw.apaw_practice.adapters.rest.music_festival;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.music_festival.ConcertArtist;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

@RestTestConfig
class ConcertArtistResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testSearchByNationality() {
        this.webTestClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder.path(ConcertArtistResource.ARTISTS + ConcertArtistResource.SEARCH)
                                .queryParam("q", "nationality:Mexican")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(ConcertArtist.class)
                .value(artists -> assertFalse(artists.isEmpty()))
                .value(artists -> assertTrue(artists.stream()
                        .map(ConcertArtist::getNationality)
                        .allMatch(nat -> nat.contains("Mexican"))));
    }
}