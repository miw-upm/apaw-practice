package es.upm.miw.apaw_practice.adapters.rest.music_manager;

import es.upm.miw.apaw_practice.domain.services.music_manager.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(SongResource.SONGS)
public class SongResource {
    static final String SONGS = "/music_manager/songs";
    static final String ID_ID = "/{id}";

    SongService songService;

    @Autowired
    public SongResource(SongService songService) {
        this.songService = songService;
    }

    @DeleteMapping(ID_ID)
    public void delete(@PathVariable String id) { this.songService.delete(id); }
}