package es.upm.miw.apaw_practice.adapters.mongodb.music.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.music.MusicSeederService;
import es.upm.miw.apaw_practice.domain.models.music.MusicGenre;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class MusicGenrePersistenceMongodbIT {

    @Autowired
    private MusicGenrePersistenceMongodb musicGenrePersistenceMongodb;

    @Autowired
    private MusicSeederService musicSeederService;

    @AfterEach
    void resetDB() {
        this.musicSeederService.deleteAll();
        this.musicSeederService.seedDatabase();
    }

    @Test
    void testCreateMusicGenre() {
        MusicGenre musicGenre = this.musicGenrePersistenceMongodb.create(new MusicGenre("TestMusicGenre", "TestDescription", 5, "TestCountry"));
        MusicGenre createdMusicGenre = this.musicGenrePersistenceMongodb.create(musicGenre);
        assertNotNull(createdMusicGenre);
        assertEquals(musicGenre.getType(), createdMusicGenre.getType());
        assertEquals(musicGenre.getDescription(), createdMusicGenre.getDescription());
        assertEquals(musicGenre.getPopularity(), createdMusicGenre.getPopularity());
        assertEquals(musicGenre.getCountryOrigin(), createdMusicGenre.getCountryOrigin());
    }

    @Test
    void testExistsMusicGenreType() {
        assertTrue(this.musicGenrePersistenceMongodb.existsMusicGenreType("rock"));
    }

    @Test
    void testNotExistsMusicGenreType() {
        assertFalse(this.musicGenrePersistenceMongodb.existsMusicGenreType("APAW"));
    }

    @Test
    void testUpdateMusicGenrePopularity() {
        MusicGenre musicGenre = this.musicGenrePersistenceMongodb.updatePopularityByType("trap");
        assertNotNull(musicGenre);
        assertEquals("trap", musicGenre.getType());
        assertEquals("trap music", musicGenre.getDescription());
        assertEquals(7, musicGenre.getPopularity());
        assertEquals("United States", musicGenre.getCountryOrigin());
    }


}
