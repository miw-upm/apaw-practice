package es.upm.miw.apaw.adapters.mongodb.football.persistence;

import es.upm.miw.apaw.adapters.mongodb.football.daos.MatchRepository;
import es.upm.miw.apaw.adapters.mongodb.football.entities.MatchEntity;
import es.upm.miw.apaw.domain.models.football.Match;
import es.upm.miw.apaw.domain.persistenceports.football.MatchPersistence;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("matchPersistence")
public class MatchPersistenceMongodb implements MatchPersistence {

    private final MatchRepository matchRepository;

    public MatchPersistenceMongodb(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    @Override
    public Optional<Match> findByMatchId(Long matchId) {
        return this.matchRepository.findByMatchId(matchId).map(MatchEntity::toMatch);
    }

    @Override
    public List<Match> readAll() {
        return this.matchRepository.findAll().stream()
                .map(MatchEntity::toMatch)
                .toList();
    }
}
