package es.upm.miw.apaw_practice.adapters.rest.music_manager;

import es.upm.miw.apaw_practice.adapters.mongodb.music_manager.daos.AlbumRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.music_manager.daos.BandRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.music_manager.entities.AlbumEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.music_manager.entities.BandEntity;
import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.music_manager.Album;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RestTestConfig
class AlbumResourceIT {

    @Autowired
    private WebTestClient webTestClient;
    @Autowired
    private AlbumRepository albumRepository;
    @Autowired
    private BandRepository bandRepository;

    @Test
    void testRead() {
        this.webTestClient
                .get()
                .uri(AlbumResource.ALBUMS + "/Nevermind" )
                .exchange()
                .expectStatus().isOk()
                .expectBody(Album.class)
                .consumeWith(albumResponse -> {
                    assertNotNull(albumResponse.getResponseBody());
                    Album album = albumResponse.getResponseBody();
                    assertEquals("Nevermind", album.getAlbumTitle());
                    assertEquals("DGC", album.getLabel());
                    assertEquals(new BigDecimal("8.99"), album.getPrice());
                    assertEquals(LocalDate.of(1991, 9, 24), album.getReleaseDate());
                });
    }
}
