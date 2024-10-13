package es.upm.miw.apaw_practice.adapters.mongodb.movies.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.movies.entities.StudioEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudioRepository extends MongoRepository<StudioEntity, String> {
}
