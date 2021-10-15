package es.upm.miw.apaw_practice.domain.persistence_ports.football;

import es.upm.miw.apaw_practice.domain.models.football.MatchWeatherDto;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchPersistence {
    Integer delete(Integer round);

    void update(MatchWeatherDto matchWeatherDto);
}
