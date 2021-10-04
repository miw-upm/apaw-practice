package es.upm.miw.apaw_practice.adapters.mongodb.football.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.football.entities.FootballPlayerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FootballPlayerRepository extends MongoRepository<FootballPlayerEntity, String> {
}
