package es.upm.miw.apaw.domain.services.football;

import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.football.Match;
import es.upm.miw.apaw.domain.persistenceports.football.MatchPersistence;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchService {

    private final MatchPersistence matchPersistence;

    public MatchService(MatchPersistence matchPersistence) {
        this.matchPersistence = matchPersistence;
    }

    public List<Match> readAll() {
        return this.matchPersistence.readAll();
    }

    public Match readByMatchId(Long matchId) {
        return this.matchPersistence.findByMatchId(matchId)
                .orElseThrow(() -> new NotFoundException("Match id: " + matchId));
    }
}