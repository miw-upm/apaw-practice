package es.upm.miw.apaw_practice.adapters.mongodb.music_manager.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.music_manager.Band;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class BandPersistenceMongodbIT {

    @Autowired
    private BandPersistenceMongodb bandPersistenceMongodb;

    @Test
    void testReadNotFound() {
        assertThrows(NotFoundException.class, () -> this.bandPersistenceMongodb.read("The Who"));
    }

    @Test
    void testRead() {
        Band band = this.bandPersistenceMongodb.read("The Beatles");
        assertEquals("The Beatles", band.getBandName());
        assertFalse(band.getActive());
        assertEquals("Liverpool, England", band.getOrigin());
        assertEquals(4, band.getArtists().size());
    }

    @Test
    void testUpdate() {
        Band band = this.bandPersistenceMongodb.read("Nirvana");
        assertEquals("Aberdeen, Washington, U.S.", band.getOrigin());
        band.setOrigin("Aberdeen, Washington, U.S.A.");
        band = this.bandPersistenceMongodb.update(band.getBandName(), band);
        assertEquals("Nirvana", band.getBandName());
        assertEquals("Aberdeen, Washington, U.S.A.", band.getOrigin());
        assertFalse(band.getActive());
        assertEquals(3, band.getArtists().size());
        band = this.bandPersistenceMongodb.read("Nirvana");
        assertEquals("Nirvana", band.getBandName());
        assertEquals("Aberdeen, Washington, U.S.A.", band.getOrigin());
        assertEquals(3, band.getArtists().size());
        assertFalse(band.getActive());
        band.setOrigin("Aberdeen, Washington, U.S.");
        this.bandPersistenceMongodb.update(band.getBandName(), band);
    }
}