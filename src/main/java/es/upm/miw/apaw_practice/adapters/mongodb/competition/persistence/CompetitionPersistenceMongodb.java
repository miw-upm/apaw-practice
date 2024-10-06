package es.upm.miw.apaw_practice.adapters.mongodb.competition.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.competition.daos.CompetitionRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.competition.entities.CompetitionEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.competition.Competition;
import es.upm.miw.apaw_practice.domain.persistence_ports.competition.CompetitionPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository("competitionPersistence")
public class CompetitionPersistenceMongodb implements CompetitionPersistence {

    private final CompetitionRepository competitionRepository;

    @Autowired
    public CompetitionPersistenceMongodb(CompetitionRepository competitionRepository) {
        this.competitionRepository = competitionRepository;
    }


    @Override
    public Stream<Competition> readAll() {
        return this.competitionRepository.findAll().stream()
                .map(CompetitionEntity::toCompetition);
    }

    @Override
    public Competition readById(String id) {
        return this.competitionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Competition with id " + id + " not found"))
                .toCompetition();
    }
}
