package es.upm.miw.apaw_practice.adapters.rest.cinema;

import es.upm.miw.apaw_practice.domain.models.cinema.Actor;
import es.upm.miw.apaw_practice.domain.services.cinema.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ActorResource.ACTOR)
public class ActorResource {

    static final String ACTOR = "/cinema/actors";
    static final String AGE_ID = "/{age}";
    static final String SPECTATORS_NAME = "/films/screens/spectators/names";

    private final ActorService actorService;

    @Autowired
    public ActorResource(ActorService actorService) {
        this.actorService = actorService;
    }

    @PostMapping
    public Actor create(@RequestBody Actor actor) {
        return this.actorService.create(actor);
    }

    @PatchMapping
    public void updateActorAge(@RequestBody List<Actor> actor) {
        this.actorService.updateAge(actor.stream());
    }

    @GetMapping(AGE_ID + SPECTATORS_NAME)
    public List<String> getSpectatorsNamesByAge(@PathVariable Integer age) {
        return this.actorService.getSpectatorsNamesByAge(age);
    }

}
