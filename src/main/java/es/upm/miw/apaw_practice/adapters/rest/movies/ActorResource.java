package es.upm.miw.apaw_practice.adapters.rest.movies;

import es.upm.miw.apaw_practice.domain.models.movies.Actor;
import es.upm.miw.apaw_practice.domain.services.movies.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ActorResource.ACTORS)
public class ActorResource {
    static final String ACTORS = "/movies/actors";

    private final ActorService actorService;

    @Autowired
    public ActorResource(ActorService actorService) { this.actorService = actorService; }

    @GetMapping("/{artisticName}")
    public Actor getActorByArtisticName(@PathVariable String artisticName) {
        return this.actorService.findByArtisticName(artisticName);
    }

    @PutMapping
    public void update(@RequestBody Actor actor) {
        this.actorService.updateActor(actor);
    }

    @PatchMapping
    public void updateAvailability(@RequestParam String artisticName, @RequestParam boolean availability){
        this.actorService.updateAvailability(artisticName, availability);
    }
}
