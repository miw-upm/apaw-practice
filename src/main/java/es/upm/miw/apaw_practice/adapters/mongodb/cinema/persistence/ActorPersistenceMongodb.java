package es.upm.miw.apaw_practice.adapters.mongodb.cinema.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.cinema.daos.ActorRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.cinema.entities.ActorEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.cinema.Actor;
import es.upm.miw.apaw_practice.domain.persistence_ports.cinema.ActorPersistence;
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
    public Actor create(Actor actor) {
        return this.actorRepository
                .save(new ActorEntity(actor))
                .toActor();
    }

    @Override
    public Actor update(Integer age, Actor actor) {
        ActorEntity actorEntity = this.actorRepository
                .findByNameAndFamilyName(actor.getName(), actor.getFamilyName())
                .orElseThrow(() -> new NotFoundException("Actor : " + actor.getName() + " " + actor.getFamilyName()));
        actorEntity.fromActor(actor);
        return this.actorRepository
                .save(actorEntity)
                .toActor();
    }

    @Override
    public Actor read(Actor actor) {
        return this.actorRepository
                .findByNameAndFamilyName(actor.getName(), actor.getFamilyName())
                .orElseThrow(() -> new NotFoundException("Actor : " + actor.getName() + " " + actor.getFamilyName()))
                .toActor();
    }

}
