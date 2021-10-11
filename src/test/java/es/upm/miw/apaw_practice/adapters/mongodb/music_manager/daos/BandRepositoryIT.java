package es.upm.miw.apaw_practice.adapters.mongodb.music_manager.daos;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
class BandRepositoryIT {

    @Autowired
    BandRepository bandRepository;

    @Test
    void testCreateAndRead() {
        assertTrue(this.bandRepository.findAll().stream()
                .anyMatch(band ->
                        "Nirvana".equals(band.getBandName()) &&
                                band.getId() != null &&
                                !band.getActive() &&
                                band.getOrigin().equals("Aberdeen, Washington, U.S.") &&
                                band.getArtistEntities() != null));
    }
}