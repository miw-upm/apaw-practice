package es.upm.miw.apaw_practice.adapters.mongodb.competition.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.competition.entities.TeamCompetitionEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TeamCompetitionRepository extends MongoRepository<TeamCompetitionEntity, String> {
}
