package es.upm.miw.apaw_practice.adapters.mongodb.music_manager.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.music_manager.Artist;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestConfig
class ArtistPersistenceMongodbIT {

    @Autowired
    private ArtistPersistenceMongodb artistPersistenceMongodb;

    @Test
    void testReadNotFound() {
        assertThrows(NotFoundException.class, () -> this.artistPersistenceMongodb.readByFirstNameAndFamilyName("Aaa", "Bbbb"));
    }

    @Test
    void testReadByFirstNameAndFamilyName() {
        Artist artist = this.artistPersistenceMongodb.readByFirstNameAndFamilyName("Kurt", "Cobain");
        assertEquals("Kurt", artist.getFirstName());
        assertEquals("Cobain", artist.getFamilyName());
        assertEquals(27, artist.getAge());
    }

    @Test
    void testUpdate() {
        Artist artist = this.artistPersistenceMongodb.readByFirstNameAndFamilyName("Dave", "Grohl");
        assertEquals(52, artist.getAge());
        artist.setAge(53);
        artist = this.artistPersistenceMongodb.update(artist);
        assertEquals("Dave", artist.getFirstName());
        assertEquals("Grohl", artist.getFamilyName());
        assertEquals(53, artist.getAge());
        artist = this.artistPersistenceMongodb.readByFirstNameAndFamilyName("Dave", "Grohl");
        assertEquals("Dave", artist.getFirstName());
        assertEquals("Grohl", artist.getFamilyName());
        assertEquals(53, artist.getAge());
        artist.setAge(52);
        this.artistPersistenceMongodb.update(artist);
    }
}