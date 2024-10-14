package es.upm.miw.apaw_practice.adapters.mongodb.movies.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.movies.daos.ActorRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.movies.entities.ActorEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.movies.Actor;
import es.upm.miw.apaw_practice.domain.persistence_ports.movies.ActorPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("actorPersistence")
public class ActorPersistenceMongodb implements ActorPersistence {

    private final ActorRepository actorRepository;

    @Autowired
    public ActorPersistenceMongodb(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    @Override
    public void update(Actor actor) {
        ActorEntity actorEntity = this.actorRepository
                .findByArtisticName(actor.getArtisticName())
                .orElseThrow(() -> new NotFoundException("Actor artistic name: " + actor.getArtisticName()));
        actorEntity.fromActor(actor);
        this.actorRepository.save(actorEntity);
    }

    @Override
    public Actor findByArtisticName(String artisticName) {
        return this.actorRepository.findByArtisticName(artisticName)
                .orElseThrow(() -> new NotFoundException("Actor artistic name: " + artisticName))
                .toActor();
    }
}
