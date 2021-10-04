package es.upm.miw.apaw_practice.adapters.mongodb.football.daos;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class StadiumRepositoryIT {

    @Autowired
    private StadiumRepository stadiumRepository;

    @Test
    void testCreateAndRead() {
        assertTrue(this.stadiumRepository.findAll().stream()
                .anyMatch(stadium ->
                        "Madrid".equals(stadium.getCity()) &&
                        "Bernabeu".equals(stadium.getName()) &&
                        "Real Madrid".equals(stadium.getTeam()) &&
                        "cloudy".equals(stadium.getMatchEntities().get(0).getWeather()) &&
                        3 == stadium.getMatchEntities().size()
                ));
    }

}
