package es.upm.miw.apaw_practice.domain.services.music_manager;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class ArtistServiceIT {

    @Autowired
    private ArtistService artistService;

    @Test
    void testFindArtistsSumAgeBySongGenre() {
        assertEquals(258, artistService.findSumAgeBySongGenre("Folk"));
        assertEquals(424, artistService.findSumAgeBySongGenre("Hard Rock"));
    }
}
