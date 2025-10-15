package es.upm.miw.apaw.adapters.resources.videogame;

import es.upm.miw.apaw.adapters.mongodb.videogame.entities.VideogameEntity;
import es.upm.miw.apaw.domain.models.videogame.Genre;
import es.upm.miw.apaw.domain.services.videogame.VideogameService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(VideogameResource.VIDEOGAMES)
public class VideogameResource {

    public static final String VIDEOGAMES = "/videogame/videogames";
    public static final String NAME_ID = "/{name}";
    static final String GENRE_TYPE = "/genre/{genreType}/online";

    private final VideogameService videogameService;

    @Autowired
    public VideogameResource(VideogameService videogameService){
        this.videogameService = videogameService;
    }

    @DeleteMapping(NAME_ID)
    public void delete(@PathVariable String name){
        this.videogameService.delete(name);
    }

    @GetMapping("/genre/{genreType}")
    public List<VideogameEntity> getByGenre(@PathVariable String genreType) {
        return videogameService.getByGenre(genreType);
    }

    @PatchMapping("/genre/{genreType}/online")
    public void updateOnlineByGenre(@PathVariable String genreType,
                                    @RequestParam boolean online) {
        videogameService.setOnlineByGenre(genreType, online);
    }
}
