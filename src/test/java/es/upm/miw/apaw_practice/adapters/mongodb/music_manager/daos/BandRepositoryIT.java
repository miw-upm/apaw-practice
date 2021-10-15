package es.upm.miw.apaw_practice.adapters.mongodb.music_manager.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.music_manager.entities.BandEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class BandRepositoryIT {

    @Autowired
    BandRepository bandRepository;

    @Test
    void testCreateAndRead() {
        assertTrue(this.bandRepository.findAll().stream()
                .anyMatch(band ->
                        "The Beatles".equals(band.getBandName()) &&
                                band.getId() != null &&
                                !band.getActive() &&
                                band.getOrigin().equals("Liverpool, England") &&
                                band.getArtistEntities().size() == 4));
    }

    @Test
    void testFindByBandName() {
        assertTrue(this.bandRepository.findByBandName("The Beatles").isPresent());
        BandEntity band = bandRepository.findByBandName("The Beatles").get();
        assertEquals("The Beatles", band.getBandName());
        assertFalse(band.getActive());
        assertEquals("Liverpool, England", band.getOrigin());
        assertEquals(4, band.getArtistEntities().size());
    }
}
