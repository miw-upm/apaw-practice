package es.upm.miw.apaw.adapters.resources.music;

import es.upm.miw.apaw.domain.models.music.Artist;
import es.upm.miw.apaw.domain.services.music.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
