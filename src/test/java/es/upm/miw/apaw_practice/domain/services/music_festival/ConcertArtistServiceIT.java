package es.upm.miw.apaw_practice.domain.services.music_festival;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.music_festival.ConcertArtist;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@TestConfig
class ConcertArtistServiceIT {

    @Autowired
    private ConcertArtistService concertArtistService;

    @Test
    void testFindByNationality() {
        Stream<ConcertArtist> stream = this.concertArtistService.findByNationality("Argentinian");
        List<ConcertArtist> artists = stream.toList();
        assertFalse(artists.isEmpty());
        artists.forEach(artist -> assertEquals("Argentinian", artist.getNationality()));
    }
}