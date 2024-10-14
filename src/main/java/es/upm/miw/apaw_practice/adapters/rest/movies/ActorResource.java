package es.upm.miw.apaw_practice.adapters.rest.movies;

import es.upm.miw.apaw_practice.domain.services.movies.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ActorResource.ACTORS)
public class ActorResource {
    static final String ACTORS = "/movies/actors";

    private final ActorService actorService;

    @Autowired
    public ActorResource(ActorService actorService) { this.actorService = actorService; }

    @PatchMapping
    public void updateAvailability(@RequestParam String artisticName, @RequestParam boolean availability){
        this.actorService.updateAvailability(artisticName, availability);
    }
}
