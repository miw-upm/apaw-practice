package es.upm.miw.apaw_practice.domain.persistence_ports.football;

import es.upm.miw.apaw_practice.domain.models.football.FootballPlayer;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface FootballPlayerPersistence {
    Stream<FootballPlayer> readAll();
}
