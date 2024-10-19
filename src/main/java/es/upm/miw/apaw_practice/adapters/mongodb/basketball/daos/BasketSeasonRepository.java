package es.upm.miw.apaw_practice.adapters.mongodb.basketball.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.basketball.entities.BasketSeasonEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BasketSeasonRepository extends MongoRepository<BasketSeasonEntity, String> {
    Optional<BasketSeasonEntity> findByLeague(String league);
}
