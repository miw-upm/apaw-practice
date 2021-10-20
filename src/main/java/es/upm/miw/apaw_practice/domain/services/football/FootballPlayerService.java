package es.upm.miw.apaw_practice.domain.services.football;

import es.upm.miw.apaw_practice.domain.models.football.FootballPlayer;
import es.upm.miw.apaw_practice.domain.persistence_ports.football.FootballPlayerPersistence;
import es.upm.miw.apaw_practice.domain.persistence_ports.football.MatchPersistence;
import es.upm.miw.apaw_practice.domain.persistence_ports.football.StadiumPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class FootballPlayerService {
    private final FootballPlayerPersistence footballPlayerPersistence;
    private final StadiumPersistence stadiumPersistence;
    private final MatchPersistence matchPersistence;

    @Autowired
    public FootballPlayerService(FootballPlayerPersistence footballPlayerPersistence, StadiumPersistence stadiumPersistence, MatchPersistence matchPersistence) {
        this.footballPlayerPersistence = footballPlayerPersistence;
        this.stadiumPersistence = stadiumPersistence;
        this.matchPersistence = matchPersistence;
    }

    public Stream<FootballPlayer> readAll() {
        return this.footballPlayerPersistence.readAll();
    }

    public Integer findGoalsByStadiumName(String q) {
        return this.stadiumPersistence.readByName(q)
                .getMatches().stream()
                .flatMap(match -> match.getPlayers().stream())
                .distinct()
                .map(FootballPlayer::getGoalsScored)
                .reduce(0, Integer::sum);
    }

    public Stream<FootballPlayer> findFootballPlayersByPrincipalRefereeName(String q) {
        return this.matchPersistence.readAll()
                .filter(match -> match.getPrincipalReferee().getName().equals(q))
                .flatMap(match -> match.getPlayers().stream())
                .distinct()
                .filter(FootballPlayer::isDefense);
    }
}
