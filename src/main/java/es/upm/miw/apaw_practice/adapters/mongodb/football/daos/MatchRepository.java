package es.upm.miw.apaw_practice.adapters.mongodb.football.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.football.entities.MatchEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MatchRepository extends MongoRepository<MatchEntity, String> {
}
