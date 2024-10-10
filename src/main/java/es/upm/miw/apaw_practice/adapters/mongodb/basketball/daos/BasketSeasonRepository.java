package es.upm.miw.apaw_practice.adapters.mongodb.basketball.daos;

import es.upm.miw.apaw_practice.domain.models.basketball.BasketSeason;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BasketSeasonRepository extends MongoRepository<BasketSeason, String> {
}
