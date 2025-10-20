package es.upm.miw.apaw.adapters.resources.music;

import es.upm.miw.apaw.domain.models.music.Artist;
import es.upm.miw.apaw.domain.services.music.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ArtistResource.ARTISTS)
public class ArtistResource {

    public static final String ARTISTS = "/music/artists";
    public static final String ARTIST_ID = "/{name}";

    private final ArtistService artistService;

    @Autowired
    public ArtistResource(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping(ARTIST_ID)
    public Artist read(@PathVariable String name) {
        return this.artistService.readByName(name);
    }

    @GetMapping(value = "/moods", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> findMoodsByUserMobile(@RequestParam String mobile) {
        List<String> moods = this.artistService.findMoodsByUserMobile(mobile).toList();
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(moods);
    }
}
