package es.upm.miw.apaw_practice.adapters.mongodb.art_museum.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.art_museum.ArtMuseumSeederService;
import es.upm.miw.apaw_practice.domain.models.art_museum.Artist;
import es.upm.miw.apaw_practice.domain.models.art_museum.Artwork;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class ArtworkPersistenceMongodbIT {
    @Autowired
    private ArtworkPersistenceMongodb artworkPersistenceMongodb;

    @Autowired
    private ArtMuseumSeederService artMuseumSeederService;

    @Test
    void testReadByInventoryNumber() {
        Optional<Artwork> artwork = Optional.ofNullable(this.artworkPersistenceMongodb.readByInventoryNumber("27001"));
        assertTrue(artwork.isPresent());
        assertNotNull(artwork.get().getInventoryNumber());
    }

    @Test
    void testUpdate() {
        Optional<Artwork> artwork = Optional.ofNullable(this.artworkPersistenceMongodb.readByInventoryNumber("27001"));
        assertTrue(artwork.isPresent());
        artwork.get().setTitleName("Monalisa");
        artwork.get().setYear(1503);
        Artist artist = new Artist("Leonardo di ser Piero da Vinci", 66, "Italian Renaissance");
        artwork.get().setArtist(artist);
        this.artworkPersistenceMongodb.update(artwork.get());

        Optional<Artwork> updatedArtwork = Optional.ofNullable(this.artworkPersistenceMongodb.readByInventoryNumber("27001"));
        assertTrue(updatedArtwork.isPresent());
        assertEquals(artwork.get().getTitleName(), updatedArtwork.get().getTitleName());
        assertEquals(artwork.get().getYear(), updatedArtwork.get().getYear());
        assertEquals(artwork.get().getArtist(), artist);

        artMuseumSeederService.deleteAll();
        artMuseumSeederService.seedDatabase();
    }
}
