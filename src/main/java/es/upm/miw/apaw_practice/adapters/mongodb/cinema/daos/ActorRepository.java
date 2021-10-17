package es.upm.miw.apaw_practice.adapters.mongodb.cinema.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.cinema.entities.ActorEntity;
import es.upm.miw.apaw_practice.domain.models.cinema.Actor;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ActorRepository extends MongoRepository<ActorEntity, String> {
    Optional<ActorEntity> findByNameAndFamilyName(String name, String familyName);
}
