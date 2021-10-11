package es.upm.miw.apaw_practice.adapters.rest.music_manager;

import es.upm.miw.apaw_practice.domain.models.music_manager.Artist;
import es.upm.miw.apaw_practice.domain.services.music_manager.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ArtistResource.ARTISTS)
public class ArtistResource {
    static final String ARTISTS = "/music_manager/artists";
    static final String ID_ID = "/{id}";

    ArtistService artistService;

    @Autowired
    public ArtistResource(ArtistService artistService) {
        this.artistService = artistService;
    }

    @PutMapping(ID_ID)
    public void delete(@PathVariable String id, @RequestBody Artist artist) {
        this.artistService.update(id, artist);
    }
}