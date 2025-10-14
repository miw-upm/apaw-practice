package es.upm.miw.apaw.adapters.resources.videogame;

import es.upm.miw.apaw.domain.models.videogame.Genre;
import es.upm.miw.apaw.domain.services.videogame.VideogameService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(VideogameResource.VIDEOGAMES)
public class VideogameResource {

    public static final String VIDEOGAMES = "/videogame/videogame";
    public static final String NAME_ID = "/{name}";

    private final VideogameService videogameService;

    @Autowired
    public VideogameResource(VideogameService videogameService){
        this.videogameService = videogameService;
    }

    @DeleteMapping(NAME_ID)
    public void delete(@PathVariable String name){
        this.videogameService.delete(name);
    }


    @PatchMapping("/videogames/genre/{genreName}/online")
    public void updateOnlineByGenre(@PathVariable String genre, @RequestBody Map<String, Boolean> body) {
        Boolean newOnlineValue = body.get("online");
        this.videogameService.updateOnlineByGenre(genre, newOnlineValue);
    }
}
