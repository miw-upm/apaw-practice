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

    BandEntity fooFighters;

    @BeforeEach
    void initAlbum() {
         fooFighters = this.bandRepository.findAll().stream()
                .filter(band -> band.getBandName().equals("Foo Fighters"))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Band Foo Fighters not found"));

        AlbumEntity albumEntity = new AlbumEntity(fooFighters, List.of(),
                "Medicine at Midnight", "RCA Records",
                new BigDecimal("11.99"), LocalDate.of(2021, 2, 5));

        albumEntity.setId("medicine");
        this.albumRepository.save(albumEntity);
    }

    @Test
    void testRead() {
        this.webTestClient
                .get()
                .uri(AlbumResource.ALBUMS + "/medicine" )
                .exchange()
                .expectStatus().isOk()
                .expectBody(Album.class)
                .consumeWith(albumResponse -> {
                    assertNotNull(albumResponse.getResponseBody());
                    Album album = albumResponse.getResponseBody();
                    assertEquals("Medicine at Midnight", album.getAlbumTitle());
                    assertEquals("RCA Records", album.getLabel());
                    assertEquals(new BigDecimal("11.99"), album.getPrice());
                    assertEquals(LocalDate.of(2021, 2, 5), album.getReleaseDate());
                    assertEquals(List.of(), album.getTracks());
                });
    }
}
