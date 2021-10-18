package es.upm.miw.apaw_practice.domain.services.cinema;

import es.upm.miw.apaw_practice.domain.models.cinema.Actor;
import es.upm.miw.apaw_practice.domain.persistence_ports.cinema.ActorPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.stream.Stream;


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

    public void updateAge(Stream<Actor> actorStream) {
        actorStream.map(actorNewAge -> {
            Actor actor = this.actorPersistence.read(actorNewAge);
            actor.setAge(actorNewAge.getAge());
            return actor;
        })
                .forEach(actor -> this.actorPersistence.update(actor.getAge(), actor));
    }
}
