package es.upm.miw.apaw_practice.adapters.mongodb.art_museum.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.art_museum.entities.ArtistEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
class ArtistRepositoryIT {
    @Autowired
    private ArtistRepository artistRepository;

    @Test
    void testFindByArtistName() {
        assertTrue(this.artistRepository.findByArtistName("Pablo Picasso").isPresent());
        ArtistEntity artist = this.artistRepository.findByArtistName("Pablo Picasso").get();
        assertEquals(91, artist.getAge());
        assertEquals("Cubism", artist.getArtStyle());
    }
}
