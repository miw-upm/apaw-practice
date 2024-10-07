package es.upm.miw.apaw_practice.domain.persistence_ports.competition;

import es.upm.miw.apaw_practice.domain.models.competition.PlayerTeam;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface PlayerTeamPersistence {
    void delete(String id);

    Stream<PlayerTeam> readAll();
}
