package es.upm.miw.apaw_practice.adapters.rest.music_manager;

import es.upm.miw.apaw_practice.adapters.mongodb.music_manager.daos.BandRepository;
import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.music_manager.Artist;
import es.upm.miw.apaw_practice.domain.models.music_manager.Band;
import es.upm.miw.apaw_practice.domain.models.music_manager.BandActiveUpdating;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.util.List;

@RestTestConfig
class BandResourceIT {

    @Autowired
    private WebTestClient webTestClient;
    @Autowired
    private BandRepository bandRepository;

    @Test
    void testCreate() {
        Band band = new Band("The Strokes", "New York City, New York, U.S.", true,
                List.of(Artist.builder().firstName("Julian").familyName("Casablancas").age(43).build(),
                        Artist.builder().firstName("Nikolai").familyName("Fraiture").age(42).build(),
                        Artist.builder().firstName("Fabrizio").familyName("Moretti").age(41).build(),
                        Artist.builder().firstName("Albert").familyName("Hammond").age(41).build()));

        this.webTestClient
                .post()
                .uri(BandResource.BANDS)
                .body(BodyInserters.fromValue(band))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Band.class)
                .value(Assertions::assertNotNull);
    }

    @Test
    void testUpdateActives() {
        List<BandActiveUpdating> bandActiveUpdatingList = List.of();

        this.webTestClient
                .patch()
                .uri(BandResource.BANDS)
                .body(BodyInserters.fromValue(bandActiveUpdatingList))
                .exchange()
                .expectStatus().isOk();
    }
}