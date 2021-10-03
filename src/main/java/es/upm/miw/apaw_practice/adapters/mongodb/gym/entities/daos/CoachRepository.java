package es.upm.miw.apaw_practice.adapters.mongodb.gym.entities.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.gym.entities.CoachEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CoachRepository extends MongoRepository<CoachEntity,String> {
}
