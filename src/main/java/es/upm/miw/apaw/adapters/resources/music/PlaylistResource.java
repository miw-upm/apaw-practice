package es.upm.miw.apaw.adapters.resources.music;

import es.upm.miw.apaw.domain.models.music.Playlist;
import es.upm.miw.apaw.domain.services.music.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(PlaylistResource.PLAYLISTS)
public class PlaylistResource {
    public static final String PLAYLISTS = "/music/playlists";
    public static final String PLAYLIST_ID = "/{code}";

    private final PlaylistService playlistService;

    @Autowired
    public PlaylistResource(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @DeleteMapping(PLAYLIST_ID)
    public void delete(@PathVariable String code) {
        this.playlistService.delete(code);
    }

    @PutMapping(PLAYLIST_ID)
    public void update(@PathVariable String code, @Validated @RequestBody Playlist playlist) {
        this.playlistService.update(code, playlist);
    }

    @GetMapping(value = "/artist-names", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> findArtistNamesByLabel(@RequestParam String label) {
        java.util.List<String> names = this.playlistService.findArtistNamesByLabel(label).toList();
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(names);
    }
}
