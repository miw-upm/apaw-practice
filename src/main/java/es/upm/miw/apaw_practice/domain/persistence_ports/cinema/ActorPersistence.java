package es.upm.miw.apaw_practice.domain.persistence_ports.cinema;

import es.upm.miw.apaw_practice.domain.models.cinema.Actor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActorPersistence {
    Actor create(Actor actor);

    Actor update(Integer age, Actor actor);

    Actor read(Actor actorNewAge);

    List<String> getSpectatorsNamesByAge(Integer age);
}
