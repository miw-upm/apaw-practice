package es.upm.miw.apaw_practice.adapters.mongodb.art_museum.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.art_museum.Artist;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class ArtistPersistenceMongodbIT {
    @Autowired
    private ArtistPersistenceMongodb artistPersistence;

    @Test
    void testArtistNameExist() {
        assertTrue(this.artistPersistence.existArtistName("Pablo Picasso"));
    }

    @Test
    void testArtistNameNotExist() {
        assertFalse(this.artistPersistence.existArtistName("Salvador Dali"));
    }

    @Test
    void testCreate() {
        Artist artist = new Artist("Francisco de Goya", 74, "Baroque");
        Artist artistBD = this.artistPersistence.create(artist);
        assertEquals("Francisco de Goya", artistBD.getArtistName());
        assertEquals(74, artistBD.getAge());
        assertEquals("Baroque", artistBD.getArtStyle());
    }
}
