package es.upm.miw.apaw_practice.domain.services.music_manager;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.persistence_ports.music_manager.SongPersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class SongServiceIT {

    @Autowired
    private SongService songService;

    @Autowired
    private SongPersistence songPersistence;

    @Test
    void testFindSongTitlesByArtistFirstName() {
     String firstName = "Dave";

     Stream<String> songs = songService.findSongTitlesByArtistFirstName(firstName);

     assertTrue(songs.anyMatch(song -> song.equals("Smells Like Teen Spirit")));
    }


}
