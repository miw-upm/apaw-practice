package es.upm.miw.apaw_practice.domain.persistence_ports.cinema;

import es.upm.miw.apaw_practice.domain.models.cinema.Actor;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorPersistence {
    Actor create(Actor actor);

    Actor update(Integer age, Actor actor);

    Actor read(Actor actorNewAge);
}
