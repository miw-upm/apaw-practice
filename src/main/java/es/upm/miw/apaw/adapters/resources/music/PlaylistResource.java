package es.upm.miw.apaw.adapters.resources.music;

import es.upm.miw.apaw.domain.models.music.Playlist;
import es.upm.miw.apaw.domain.services.music.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
}
