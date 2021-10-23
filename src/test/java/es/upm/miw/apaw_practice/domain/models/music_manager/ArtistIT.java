package es.upm.miw.apaw_practice.domain.models.music_manager;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
class ArtistIT {
    private Artist artist;

    @BeforeEach
    void initializeArtist() {
        artist = Artist.builder().firstName("Billy").familyName("Joel").age(72).build();
    }

    @Test
    void testToString() {
        assertEquals("Artist{" +
                "firstName='" + "Billy" + '\'' +
                ", familyName='" + "Joel" + '\'' +
                ", age=" + 72 +
                '}', artist.toString());
    }

}
