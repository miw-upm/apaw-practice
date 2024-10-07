package es.upm.miw.apaw_practice.domain.persistence_ports.competition;

import es.upm.miw.apaw_practice.domain.models.competition.TeamCompetition;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface TeamCompetitionPersistence {

    Stream<TeamCompetition> readAll();

    TeamCompetition readById(String id);

    TeamCompetition update(TeamCompetition teamCompetition);
}
