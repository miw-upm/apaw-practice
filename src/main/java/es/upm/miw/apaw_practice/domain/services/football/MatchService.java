package es.upm.miw.apaw_practice.domain.services.football;

import es.upm.miw.apaw_practice.domain.models.football.MatchWeatherDto;
import es.upm.miw.apaw_practice.domain.persistence_ports.football.MatchPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchService {

    private final MatchPersistence matchPersistence;

    @Autowired
    public MatchService(MatchPersistence matchPersistence) {
        this.matchPersistence = matchPersistence;
    }

    public void delete(Integer round) {
        this.matchPersistence.delete(round);
    }

    public void updateWeather(MatchWeatherDto matchWeatherDto) {
        this.matchPersistence.update(matchWeatherDto);
    }
}
