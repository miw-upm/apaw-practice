package es.upm.miw.apaw_practice.adapters.mongodb.movies.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.movies.entities.AwardEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AwardRepository extends MongoRepository<AwardEntity, String> {
}
