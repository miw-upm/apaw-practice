package es.upm.miw.apaw_practice.adapters.mongodb.competition.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.competition.entities.CompetitionEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CompetitionRepository extends MongoRepository<CompetitionEntity, String> {
    Optional<CompetitionEntity> findByNameCompetition(String competitionName);
}
