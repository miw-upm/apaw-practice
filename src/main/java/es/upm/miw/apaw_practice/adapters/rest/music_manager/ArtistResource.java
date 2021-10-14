package es.upm.miw.apaw_practice.adapters.rest.music_manager;

import es.upm.miw.apaw_practice.domain.services.music_manager.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ArtistResource.ARTISTS)
public class ArtistResource {
    static final String ARTISTS = "/music_manager/artists";
    static final String FULLNAME = "/{fullName}";
    static final String AGE = "/age";

    ArtistService artistService;

    @Autowired
    public ArtistResource(ArtistService artistService) {
        this.artistService = artistService;
    }

    @PutMapping(FULLNAME + AGE)
    public void updateAge(@PathVariable String fullName, @RequestBody Integer age) {
        this.artistService.updateAge(fullName, age);
    }
}