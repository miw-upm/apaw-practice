package es.upm.miw.apaw_practice.adapters.mongodb.competition.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.competition.entities.TeamCompetitionEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TeamCompetitionRepository extends MongoRepository<TeamCompetitionEntity, String> {
    Optional<TeamCompetitionEntity> findByNameTeamCompetition(String nameTeamCompetition);
}
