package es.upm.miw.apaw.adapters.resources.music;

import es.upm.miw.apaw.domain.models.music.Song;
import es.upm.miw.apaw.domain.services.music.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(SongResource.SONGS)
public class SongResource {

    public static final String SONGS = "/music/songs";

    private final SongService songService;

    @Autowired
    public SongResource(SongService songService) {
        this.songService = songService;
    }

    @PostMapping
    public void create(@Validated @RequestBody Song song) {
        this.songService.create(song); // estilo shop: void ⇒ 200 OK sin body
    }
}
