package es.upm.miw.apaw_practice.domain.services.art_museum;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.art_museum.ArtMuseumSeederService;
import es.upm.miw.apaw_practice.domain.models.art_museum.Artist;
import es.upm.miw.apaw_practice.domain.models.art_museum.Artwork;
import es.upm.miw.apaw_practice.domain.persistence_ports.art_museum.ArtworkPersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
class ArtworkServiceIT {
    @Autowired
    private ArtworkService artworkService;

    @Autowired
    private ArtworkPersistence artworkPersistence;

    @Autowired
    private ArtMuseumSeederService artMuseumSeederService;

    @Test
    void testUpdate() {
        Optional<Artwork> artwork = Optional.ofNullable(this.artworkPersistence.readByInventoryNumber("27001"));
        assertTrue(artwork.isPresent());
        artwork.get().setTitleName("Monalisa");
        artwork.get().setYear(1503);
        Artist artist = new Artist("Leonardo di ser Piero da Vinci", 66, "Italian Renaissance");
        artwork.get().setArtist(artist);
        this.artworkService.update(artwork.get().getInventoryNumber(), artwork.get());

        Optional<Artwork> updatedArtwork = Optional.ofNullable(this.artworkPersistence.readByInventoryNumber("27001"));
        assertTrue(updatedArtwork.isPresent());
        assertEquals(artwork.get().getTitleName(), updatedArtwork.get().getTitleName());
        assertEquals(artwork.get().getYear(), updatedArtwork.get().getYear());
        assertEquals(artwork.get().getArtist(), artist);

        artMuseumSeederService.deleteAll();
        artMuseumSeederService.seedDatabase();
    }

}
