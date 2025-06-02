package es.upm.miw.apaw_practice.adapters.mongodb.music_festival.daos;

import static org.junit.jupiter.api.Assertions.*;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.music_festival.entities.ConcertArtistEntity;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@TestConfig
class ConcertArtistRepositoryIT {
    @Autowired
    private ConcertArtistRepository concertArtistRepository;

    @Test
    void testFindByName() {
        String name = "DJ Luna";
        Optional<ConcertArtistEntity> artistOptional = this.concertArtistRepository.findByName(name);
        assertTrue(artistOptional.isPresent());
        ConcertArtistEntity artist = artistOptional.get();
        var artistDummy = new ConcertArtistEntity(name, null, 0.0);
        assertAll(
                () -> assertNotNull(artist.getId()),
                () -> assertEquals(name, artist.getName()),
                () -> assertEquals("Española", artist.getNationality()),
                () -> assertEquals(4.2, artist.getRating()),
                () -> assertTrue(artist.toString().contains(artistDummy.getName()))
        );
    }
}