package es.upm.miw.apaw_practice.adapters.mongodb.basketball.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.basketball.entities.BasketMatchEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BasketMatchRepository extends MongoRepository<BasketMatchEntity, String> {
    List<BasketMatchEntity> findByMatchIdIn(List<Integer> idList);
}
