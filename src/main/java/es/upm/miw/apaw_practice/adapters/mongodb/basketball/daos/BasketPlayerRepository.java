package es.upm.miw.apaw_practice.adapters.mongodb.basketball.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.basketball.entities.BasketPlayerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BasketPlayerRepository extends MongoRepository<BasketPlayerEntity, String> {
}
