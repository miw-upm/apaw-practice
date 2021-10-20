package es.upm.miw.apaw_practice.adapters.mongodb.Class.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.Class.entities.LearnerEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.football.entities.StadiumEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface LearnerRepository extends MongoRepository<LearnerEntity,String> {
    Optional<LearnerEntity> findByName(String name);
}
