package es.upm.miw.apaw_practice.domain.persistence_ports.cinema;

import es.upm.miw.apaw_practice.adapters.mongodb.cinema.entities.ActorEntity;
import es.upm.miw.apaw_practice.domain.models.cinema.Actor;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorPersistence {
    Actor create(Actor actor);
}
