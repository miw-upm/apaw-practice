package es.upm.miw.apaw_practice.adapters.mongodb.art_museum.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.art_museum.entities.ArtworkEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class ArtworkRepositoryIT {
    @Autowired
    private ArtworkRepository artworkRepository;

    @Test
    void testFindByInventoryNumber() {
        assertTrue(this.artworkRepository.findByInventoryNumber("27001").isPresent());
        ArtworkEntity artwork = this.artworkRepository.findByInventoryNumber("27001").get();
        assertEquals("La Gioconda", artwork.getTitleName());
        assertEquals(1506, artwork.getYear());
        assertEquals("Leonardo da Vinci", artwork.getArtist().getArtistName());
    }
}
