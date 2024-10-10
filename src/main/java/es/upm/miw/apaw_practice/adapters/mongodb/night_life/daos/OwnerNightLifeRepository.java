package es.upm.miw.apaw_practice.adapters.mongodb.night_life.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.night_life.entities.OwnerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface OwnerNightLifeRepository extends MongoRepository<OwnerEntity, String> {
    Optional<OwnerEntity> findByName(String name);
}
