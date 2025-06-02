package es.upm.miw.apaw_practice.adapters.mongodb.music_festival.persistence;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.music_festival.ConcertArtist;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@TestConfig
class ConcertArtistPersistenceMongodbIT {
    @Autowired
    private ConcertArtistPersistenceMongodb concertArtistPersistence;

    @Test
    void testReadByName() {
        String name = "ElectroBand";
        ConcertArtist artist = this.concertArtistPersistence.readByName(name);
        var artistDummy = new ConcertArtist(name, null, 0.0);
        assertAll(
                () -> assertEquals(name, artist.getName()),
                () -> assertEquals("Argentinian", artist.getNationality()),
                () -> assertEquals(4.7, artist.getRating()),
                () -> assertTrue(artist.toString().contains(artistDummy.getName()))
        );
    }

    @Test
    void testReadByNameNotFound() {
        assertThrows(NotFoundException.class, () -> this.concertArtistPersistence.readByName("UnknownArtist"));
    }

    @Test
    void testFindByNationality() {
        var artists = this.concertArtistPersistence.findByNationality("Spanish").toList();
        assertAll(
                () -> assertFalse(artists.isEmpty()),
                () -> assertEquals("DJ Moon", artists.get(0).getName())
        );
    }
}