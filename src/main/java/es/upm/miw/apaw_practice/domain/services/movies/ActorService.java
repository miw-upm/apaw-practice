package es.upm.miw.apaw_practice.domain.services.movies;

import es.upm.miw.apaw_practice.domain.models.movies.Actor;
import es.upm.miw.apaw_practice.domain.persistence_ports.movies.ActorPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActorService {

    private final ActorPersistence actorPersistence;

    @Autowired
    public ActorService(ActorPersistence actorPersistence) { this.actorPersistence = actorPersistence; }

    public void updateAvailability(String artisticName, boolean availability) {
        Actor actor = this.actorPersistence.findByArtisticName(artisticName);
        actor.setIsAvailable(availability);
        this.actorPersistence.update(actor);
    }
}
