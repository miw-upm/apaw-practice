package es.upm.miw.apaw_practice.adapters.rest.music_manager;

import es.upm.miw.apaw_practice.adapters.mongodb.music_manager.daos.ArtistRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.music_manager.entities.ArtistEntity;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.music_manager.Album;
import es.upm.miw.apaw_practice.domain.models.music_manager.Artist;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RestTestConfig
class ArtistResourceIT {

    @Autowired
    private WebTestClient webTestClient;
    @Autowired
    private ArtistRepository artistRepository;

    private static final String ARTISTID = "myles";

    @BeforeEach
    void initArtist() {
        ArtistEntity artistEntity = new ArtistEntity(new Artist("Myles", "Kennedy", 51));
        artistEntity.setId(ARTISTID);
        artistRepository.save(artistEntity);
    }

    @Test
    void testUpdate() {
        Artist artist = new Artist("Myles", "Kennedy", 52);

        this.webTestClient
                .put()
                .uri(ArtistResource.ARTISTS + "/" + ARTISTID)
                .body(BodyInserters.fromValue(artist))
                .exchange()
                .expectStatus().isOk();
    }
}