package es.upm.miw.apaw_practice.domain.services.music_manager;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.persistence_ports.music_manager.SongPersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

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
        List<String> songTitlesList = songService
                .findSongTitlesByArtistFirstName(firstName)
                .collect(Collectors.toList());
        List<String> songTitlesExpected = List.of("Smells Like Teen Spirit", "The Pretender", "Heart-Shaped Box");

        assertTrue(songTitlesList.containsAll(songTitlesExpected) &&
                songTitlesList.size() == 3);

        firstName = "Kurt";
        songTitlesList = songService
                .findSongTitlesByArtistFirstName(firstName)
                .collect(Collectors.toList());
        songTitlesExpected = List.of("Smells Like Teen Spirit", "Heart-Shaped Box");

        assertTrue(songTitlesList.containsAll(songTitlesExpected) &&
                songTitlesList.size() == 2);
    }
}
