package es.upm.miw.apaw.adapters.resources.videogame;

import es.upm.miw.apaw.domain.services.videogame.GenreService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(GenreResource.GENRES)
public class GenreResource {
    public static final String GENRES = "/videogame/genres";
    public static final String GENRE_TYPE = "/{type}";
    private final GenreService genreService;

    @Autowired
    public GenreResource (GenreService genreService){
        this.genreService =genreService;
    }

    @PutMapping
    public void updateAgeRestriction(@Valid @RequestParam String type, @RequestParam Integer newAge){
        this.genreService.updateAgeRestriction(type,newAge);
    }
}
