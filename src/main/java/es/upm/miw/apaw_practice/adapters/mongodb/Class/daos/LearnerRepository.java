package es.upm.miw.apaw_practice.adapters.mongodb.Class.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.Class.entities.LearnerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LearnerRepository extends MongoRepository<LearnerEntity,String> {
}
