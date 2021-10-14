package es.upm.miw.apaw_practice.adapters.mongodb.football.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.football.daos.MatchRepository;
import es.upm.miw.apaw_practice.domain.models.football.MatchWeatherDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
class MatchPersistenceMongodbIT {

    @Autowired
    private MatchPersistenceMongodb matchPersistenceMongodb;

    @Autowired
    private MatchRepository matchRepository;

    @Test
    void testDelete() {
        assertEquals(1, this.matchPersistenceMongodb.delete(6));
        assertEquals(0, this.matchPersistenceMongodb.delete(8));
    }

    @Test
    void testCreateAndUpdateWeather() {
        assertEquals(0, this.matchRepository.findAll().stream()
                .filter(matchEntity -> matchEntity.getWeather().equals("foggy"))
                .count());

        MatchWeatherDto matchWeatherDto = new MatchWeatherDto(LocalDateTime.of(2021, 10, 20, 21, 0), "foggy");

        this.matchPersistenceMongodb.update(matchWeatherDto);
        assertEquals(1, this.matchRepository.findAll().stream()
                .filter(matchEntity -> matchEntity.getWeather().equals("foggy"))
                .count());

        matchWeatherDto.setWeather("cloudy");
        this.matchPersistenceMongodb.update(matchWeatherDto);
    }
}
