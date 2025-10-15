package es.upm.miw.apaw.domain.persistenceports.football;


import es.upm.miw.apaw.domain.models.football.Match;
import java.util.List;
import java.util.Optional;

public interface MatchPersistence {
    Optional<Match> findByMatchId(Long matchId);
    List<Match> readAll();
}
