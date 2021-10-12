package es.upm.miw.apaw_practice.adapters.mongodb.music_manager.daos;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
class ArtistRepositoryIT {

    @Autowired
    ArtistRepository artistRepository;

    @Test
    void testCreateAndRead() {
        assertTrue(this.artistRepository.findAll().stream()
                .anyMatch(artist ->
                        "Dave".equals(artist.getFirstName()) &&
                                artist.getId() != null &&
                                artist.getFamilyName().equals("Grohl") &&
                                artist.getAge().equals(52)));
    }
}