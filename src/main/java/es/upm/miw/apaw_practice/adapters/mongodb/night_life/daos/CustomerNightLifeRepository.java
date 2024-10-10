package es.upm.miw.apaw_practice.adapters.mongodb.night_life.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.night_life.entities.CustomerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CustomerNightLifeRepository extends MongoRepository<CustomerEntity, String> {
    int deleteByName(String name);
    Optional<CustomerEntity> findByName(String name);
}
