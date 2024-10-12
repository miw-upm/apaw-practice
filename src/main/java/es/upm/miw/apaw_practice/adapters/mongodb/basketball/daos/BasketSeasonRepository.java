package es.upm.miw.apaw_practice.adapters.mongodb.basketball.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.basketball.entities.BasketSeasonEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BasketSeasonRepository extends MongoRepository<BasketSeasonEntity, String> {
}
