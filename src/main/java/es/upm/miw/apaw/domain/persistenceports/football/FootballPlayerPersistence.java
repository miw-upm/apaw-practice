package es.upm.miw.apaw.domain.persistenceports.football;

import es.upm.miw.apaw.domain.models.football.FootballPlayer;
import java.util.List;
import java.util.Optional;

public interface FootballPlayerPersistence {
    Optional<FootballPlayer> findByNickname(String nickname);
    List<FootballPlayer> readAll();
}