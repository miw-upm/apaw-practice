package es.upm.miw.apaw_practice.domain.persistence_ports.competition;

import es.upm.miw.apaw_practice.domain.models.competition.Competition;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Stream;

@Repository
public interface CompetitionPersistence {
    Stream<Competition> readAll();

    Competition readById(String id);

    List<String> competitionNameByPlayerId(String idPlayerTeam);
}
