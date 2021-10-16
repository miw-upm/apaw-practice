package es.upm.miw.apaw_practice.adapters.mongodb.music_manager.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.music_manager.Album;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class AlbumPersistenceMongodbIT {

    @Autowired
    private AlbumPersistenceMongodb albumPersistenceMongodb;

    @Test
    void testReadNotFound() {
        assertThrows(NotFoundException.class, () -> this.albumPersistenceMongodb.read(" "));
    }

    @Test
    void testRead() {
        Album album = this.albumPersistenceMongodb.read("Nevermind");
        assertEquals("Nevermind", album.getAlbumTitle());
        assertEquals(new BigDecimal("8.99"), album.getPrice());
        assertEquals("DGC", album.getLabel());
        assertEquals(LocalDate.of(1991, 9, 24), album.getReleaseDate());
    }
}