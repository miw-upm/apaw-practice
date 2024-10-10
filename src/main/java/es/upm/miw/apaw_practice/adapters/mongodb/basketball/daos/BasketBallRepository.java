package es.upm.miw.apaw_practice.adapters.mongodb.basketball.daos;

import es.upm.miw.apaw_practice.domain.models.basketball.BasketBall;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BasketBallRepository extends MongoRepository<BasketBall, String> {
}
