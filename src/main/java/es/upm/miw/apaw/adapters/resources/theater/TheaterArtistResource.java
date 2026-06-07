package es.upm.miw.apaw.adapters.resources.theater;

import es.upm.miw.apaw.domain.models.theater.TheaterArtist;
import es.upm.miw.apaw.domain.services.theater.TheaterArtistService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(TheaterArtistResource.ARTISTS)
public class TheaterArtistResource {

    public static final String ARTISTS = "/theater/artists";

    private final TheaterArtistService theaterArtistService;

    @Autowired
    public TheaterArtistResource(TheaterArtistService theaterArtistService) {
        this.theaterArtistService = theaterArtistService;
    }

    @PostMapping
    public TheaterArtist create(@Valid @RequestBody TheaterArtist theaterArtist) {
        return this.theaterArtistService.create(theaterArtist);
    }
}
