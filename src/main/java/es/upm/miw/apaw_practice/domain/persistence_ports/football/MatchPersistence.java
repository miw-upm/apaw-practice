package es.upm.miw.apaw_practice.domain.persistence_ports.football;

import es.upm.miw.apaw_practice.domain.models.football.FootballPlayer;
import es.upm.miw.apaw_practice.domain.models.football.Match;
import es.upm.miw.apaw_practice.domain.models.football.MatchWeatherDto;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface MatchPersistence {
    Integer delete(Integer round);

    void update(MatchWeatherDto matchWeatherDto);

    Stream<Match> readAll();
}
