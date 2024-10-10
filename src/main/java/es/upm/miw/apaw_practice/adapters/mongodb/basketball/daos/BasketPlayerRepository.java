package es.upm.miw.apaw_practice.adapters.mongodb.basketball.daos;

import es.upm.miw.apaw_practice.domain.models.basketball.BasketPlayer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BasketPlayerRepository extends MongoRepository<BasketPlayer, String> {
}
