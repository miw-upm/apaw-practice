package es.upm.miw.apaw.adapters.resources.videogame;

import es.upm.miw.apaw.domain.services.videogame.VideogameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping(NAME_ID)
    public void delete(@PathVariable String name){
        this.videogameService.delete(name);
    }
}
