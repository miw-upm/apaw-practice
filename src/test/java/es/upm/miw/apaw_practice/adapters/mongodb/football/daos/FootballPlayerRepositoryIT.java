package es.upm.miw.apaw_practice.adapters.mongodb.football.daos;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class FootballPlayerRepositoryIT {

    @Autowired
    private FootballPlayerRepository footballPlayerRepository;

    @Test
    void testCreateAndRead() {
        assertTrue(this.footballPlayerRepository.findAll().stream()
                .anyMatch(player ->
                        24 == player.getAge() &&
                                10 == player.getGoalsScored() &&
                                Boolean.TRUE == player.isDefense()
                ));
    }

}
