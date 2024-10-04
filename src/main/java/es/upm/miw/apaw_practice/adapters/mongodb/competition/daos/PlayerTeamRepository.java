package es.upm.miw.apaw_practice.adapters.mongodb.competition.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.competition.entities.PlayerTeamEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlayerTeamRepository extends MongoRepository<PlayerTeamEntity, String> {
}
