package es.upm.miw.apaw_practice.domain.services.cinema;

import es.upm.miw.apaw_practice.adapters.rest.cinema.ActorResource;
import es.upm.miw.apaw_practice.domain.models.cinema.Actor;
import es.upm.miw.apaw_practice.domain.persistence_ports.cinema.ActorPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActorService {
    private final ActorPersistence actorPersistence;

    @Autowired
    public ActorService(ActorPersistence actorPersistence) {
        this.actorPersistence = actorPersistence;
    }

    public Actor create(Actor actor) {
        return this.actorPersistence.create(actor);
    }
}
