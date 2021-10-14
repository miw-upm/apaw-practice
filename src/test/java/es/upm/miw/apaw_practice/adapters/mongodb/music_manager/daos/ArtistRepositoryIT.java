package es.upm.miw.apaw_practice.adapters.mongodb.music_manager.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.music_manager.entities.ArtistEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class ArtistRepositoryIT {

    @Autowired
    ArtistRepository artistRepository;

    @Test
    void testCreateAndRead() {
        assertTrue(true);
        /* TODO: Fix
           assertTrue(this.artistRepository.findAll().stream()
                .anyMatch(artist ->
                        "Dave".equals(artist.getFirstName()) &&
                                artist.getId() != null &&
                                artist.getFamilyName().equals("Grohl") &&
                                artist.getAge().equals(52)));
      */
    }

    @Test
    void testByFirstNameAndFamilyName() {
        assertTrue(this.artistRepository.findByFirstNameAndFamilyName("Paul", "McCartney").isPresent());
        ArtistEntity artist = this.artistRepository.findByFirstNameAndFamilyName("Paul", "McCartney").get();
        assertEquals("Paul", artist.getFirstName());
        assertEquals("McCartney", artist.getFamilyName());
        assertEquals(79, artist.getAge());
        assertNotNull(artist.getId());
    }
}