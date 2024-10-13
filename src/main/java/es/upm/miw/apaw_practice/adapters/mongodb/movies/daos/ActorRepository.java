package es.upm.miw.apaw_practice.adapters.mongodb.movies.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.movies.entities.ActorEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ActorRepository extends MongoRepository<ActorEntity, String> {
    Optional<ActorEntity> findByArtisticName(String artisticName);
}
