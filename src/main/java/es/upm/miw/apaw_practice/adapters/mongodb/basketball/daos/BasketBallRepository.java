package es.upm.miw.apaw_practice.adapters.mongodb.basketball.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.basketball.entities.BasketBallEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BasketBallRepository extends MongoRepository<BasketBallEntity, String> {
    Optional<BasketBallEntity> findByBallId(Integer id);
}
