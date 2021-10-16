package es.upm.miw.apaw_practice.adapters.mongodb.football.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.football.daos.MatchRepository;
import es.upm.miw.apaw_practice.domain.models.football.MatchWeatherDto;
import es.upm.miw.apaw_practice.domain.persistence_ports.football.MatchPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("matchPersistence")
public class MatchPersistenceMongodb implements MatchPersistence {

    private final MatchRepository matchRepository;

    @Autowired
    public MatchPersistenceMongodb(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    @Override
    public Integer delete(Integer round) {
        return this.matchRepository.deleteByRound(round);
    }

    @Override
    public void update(MatchWeatherDto matchWeatherDto) {
        this.matchRepository.findAll().stream()
                .filter(matchEntity -> matchWeatherDto.getDate().equals(matchEntity.getDate()))
                .forEach(matchEntity -> {
                    matchEntity.setWeather(matchWeatherDto.getWeather());
                    this.matchRepository.save(matchEntity);
                });

    }
}
