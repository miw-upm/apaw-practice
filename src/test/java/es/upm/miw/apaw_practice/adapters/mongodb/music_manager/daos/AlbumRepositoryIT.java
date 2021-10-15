package es.upm.miw.apaw_practice.adapters.mongodb.music_manager.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.music_manager.entities.AlbumEntity;
import es.upm.miw.apaw_practice.domain.models.music_manager.Album;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class AlbumRepositoryIT {

    @Autowired
    AlbumRepository albumRepository;

    @Test
    void testCreateAndRead() {
        assertTrue(this.albumRepository.findAll().stream()
                .anyMatch(album ->
                        "The Beatles".equals(album.getAlbumTitle()) &&
                                album.getId() != null &&
                                album.getBandEntity().getBandName().equals("The Beatles") &&
                                album.getLabel().equals("Apple") &&
                                album.getPrice().equals(new BigDecimal("6.99")) &&
                                album.getReleaseDate().isEqual(LocalDate.of(1968, 11, 22))));
    }

    @Test
    void testFindByAlbumTitle() {
        assertTrue(this.albumRepository.findByAlbumTitle("The Beatles").isPresent());
        AlbumEntity album = albumRepository.findByAlbumTitle("The Beatles").get();
        assertEquals("The Beatles", album.getAlbumTitle());
        assertEquals("Apple", album.getLabel());
        assertEquals(new BigDecimal("6.99"), album.getPrice());
        assertEquals(LocalDate.of(1968, 11, 22), album.getReleaseDate());
    }
}