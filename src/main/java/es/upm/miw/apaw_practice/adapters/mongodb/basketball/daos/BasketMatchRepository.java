package es.upm.miw.apaw_practice.adapters.mongodb.basketball.daos;

import es.upm.miw.apaw_practice.domain.models.basketball.BasketMatch;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BasketMatchRepository extends MongoRepository<BasketMatch, String> {
}
