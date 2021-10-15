package es.upm.miw.apaw_practice.adapters.mongodb.gym.entities.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.gym.entities.GymEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface GymRepository extends MongoRepository<GymEntity,String> {
    Optional<GymEntity> findByLabel(String label);
}
