package es.upm.miw.apaw.domain.persistenceports.football;


import es.upm.miw.apaw.domain.models.football.FootballClub;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface FootballClubPersistence {
    Optional<FootballClub> findByName(String name);
    List<FootballClub> readAll();
    FootballClub save(FootballClub club);
    void delete(Long clubId);
    FootballClub updateBudget(Long clubId, BigDecimal newBudget);
}