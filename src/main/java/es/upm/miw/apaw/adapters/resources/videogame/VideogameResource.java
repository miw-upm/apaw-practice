package es.upm.miw.apaw.adapters.resources.videogame;

import es.upm.miw.apaw.domain.models.videogame.Videogame;
import es.upm.miw.apaw.domain.services.videogame.VideogameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(VideogameResource.VIDEOGAMES)
public class VideogameResource {

    public static final String VIDEOGAMES = "/videogame/videogames";
    public static final String NAME_ID = "/{name}";
    public static final String GENRE_TYPE = "/genre/{genreType}/online";
    public static final String GENRES = "/genre/{genreType}";
    public static final String MOBILE = "/{mobile}";

    private final VideogameService videogameService;

    @Autowired
    public VideogameResource(VideogameService videogameService) {
        this.videogameService = videogameService;
    }

    @DeleteMapping(NAME_ID)
    public void delete(@PathVariable String name) {
        this.videogameService.delete(name);
    }

    @GetMapping(GENRES)
    public List<Videogame> getByGenre(@PathVariable String genreType) {
        return videogameService.getByGenre(genreType);
    }

    @PatchMapping(GENRE_TYPE)
    public void updateOnlineByGenre(@PathVariable String genreType,
                                    @RequestParam boolean online) {
        videogameService.setOnlineByGenre(genreType, online);
    }
}
