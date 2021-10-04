package es.upm.miw.apaw_practice.adapters.mongodb.football.daos;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class MatchRepositoryIT {

    @Autowired
    private MatchRepository matchRepository;

    @Test
    void testCreateAndRead() {
        assertTrue(this.matchRepository.findAll().stream()
                .anyMatch(match ->
                        match.getDate().equals(LocalDateTime.of(2021, 10, 20, 21, 0)) &&
                        "cloudy".equals(match.getWeather()) &&
                        3 == match.getRound() &&
                        "Undiano".equals(match.getPrincipalRefereeEntity().getName()) &&
                        3 == match.getPlayerEntities().size() &&
                        20 == match.getPlayerEntities().get(1).getGoalsScored()
                ));
    }
}
