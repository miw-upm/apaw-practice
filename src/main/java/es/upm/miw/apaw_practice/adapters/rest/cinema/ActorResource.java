package es.upm.miw.apaw_practice.adapters.rest.cinema;

import es.upm.miw.apaw_practice.domain.models.cinema.Actor;
import es.upm.miw.apaw_practice.domain.services.cinema.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping(ActorResource.ACTOR)
public class ActorResource {

    static final String ACTOR = "/cinema/actor";

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

}
