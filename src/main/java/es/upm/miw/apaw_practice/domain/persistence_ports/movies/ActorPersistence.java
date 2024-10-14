package es.upm.miw.apaw_practice.domain.persistence_ports.movies;

import es.upm.miw.apaw_practice.domain.models.movies.Actor;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorPersistence {

    void update(Actor actor);

    Actor findByArtisticName(String artisticName);
}