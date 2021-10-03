package es.upm.miw.apaw_practice.adapters.mongodb.gym.entities.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.gym.entities.GymEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GymRepository extends MongoRepository<GymEntity,String> {
}
