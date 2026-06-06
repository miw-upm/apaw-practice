package es.upm.miw.apaw.domain.persistenceports.football;

import es.upm.miw.apaw.domain.models.football.FootballClub;
import java.util.List;
import java.util.Optional;

public interface FootballClubPersistence {
    Optional<FootballClub> findByName(String name);
    List<FootballClub> readAll();
    FootballClub save(FootballClub club);
    void delete(Long clubId);
    Optional<FootballClub> findByClubId(Long clubId);
}