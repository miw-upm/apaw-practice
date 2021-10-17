package es.upm.miw.apaw_practice.domain.services.football;

import es.upm.miw.apaw_practice.domain.models.football.FootballPlayer;
import es.upm.miw.apaw_practice.domain.persistence_ports.football.FootballPlayerPersistence;
import es.upm.miw.apaw_practice.domain.persistence_ports.football.StadiumPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class FootballPlayerService {
    private final FootballPlayerPersistence footballPlayerPersistence;
    private final StadiumPersistence stadiumPersistence;

    @Autowired
    public FootballPlayerService(FootballPlayerPersistence footballPlayerPersistence, StadiumPersistence stadiumPersistence) {
        this.footballPlayerPersistence = footballPlayerPersistence;
        this.stadiumPersistence = stadiumPersistence;
    }

    public Stream<FootballPlayer> readAll() {
        return this.footballPlayerPersistence.readAll();
    }

    public Integer findGoalsByStadiumName(String stadiumName) {
         return this.stadiumPersistence.readByName(stadiumName)
                 .getMatches().stream()
                 .flatMap(match -> match.getPlayers().stream().distinct())
                 .map(FootballPlayer::getGoalsScored)
                 .reduce(0, Integer::sum);
    }
}
