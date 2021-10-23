package es.upm.miw.apaw_practice.adapters.rest.music_manager;

import es.upm.miw.apaw_practice.adapters.rest.LexicalAnalyzer;
import es.upm.miw.apaw_practice.domain.models.music_manager.Song;
import es.upm.miw.apaw_practice.domain.services.music_manager.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@RestController
@RequestMapping(SongResource.SONGS)
public class SongResource {
    static final String SONGS = "/music_manager/songs";
    static final String SONGTITLE_ID = "/{songTitle}";
    static final String SONGTITLE = "/songTitle";
    static final String SEARCH = "/search";

    SongService songService;

    @Autowired
    public SongResource(SongService songService) {
        this.songService = songService;
    }

    @DeleteMapping(SONGTITLE_ID)
    public void delete(@PathVariable String songTitle) {
        this.songService.delete(songTitle);
    }

    @GetMapping(SONGTITLE + SEARCH)
    public Stream<Song> getSongTitlesByArtist(@RequestParam String q) {
        Stream<String> songTitleStream = this.songService.findSongTitlesByArtistFirstName(
                new LexicalAnalyzer().extractWithAssure(q, "firstName"));

        return songTitleStream.map(songTitle -> {
            Song song = new Song();
            song.setSongTitle(songTitle);
            return song;
        });
    }
}