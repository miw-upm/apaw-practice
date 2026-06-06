package es.upm.miw.apaw.adapters.resources.theater;

import es.upm.miw.apaw.domain.models.theater.TheaterArtist;
import es.upm.miw.apaw.domain.services.theater.TheaterArtistService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(TheaterArtistResource.ARTISTS)
public class TheaterArtistResource {

    public static final String ARTISTS = "/theater/artists";
    public static final String ARTIST_CODE = "/{artistCode}";

    private final TheaterArtistService theaterArtistService;

    @Autowired
    public TheaterArtistResource(TheaterArtistService theaterArtistService) {
        this.theaterArtistService = theaterArtistService;
    }

    @PostMapping
    public TheaterArtist create(@Valid @RequestBody TheaterArtist theaterArtist) {
        return this.theaterArtistService.create(theaterArtist);
    }

    @DeleteMapping(TheaterArtistResource.ARTIST_CODE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String artistCode) {
        this.theaterArtistService.delete(artistCode);
    }
}
