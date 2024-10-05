package es.upm.miw.apaw_practice.adapters.mongodb.competition.daos;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
class PlayerTeamRepositoryIT {

    @Autowired
    private PlayerTeamRepository playerTeamRepository;

    @Test
    void createAndRead() {
        assertTrue(this.playerTeamRepository.findAll().stream()
                .anyMatch(player ->
                        Double.valueOf("86.89").equals(player.getWeight()) &&
                                Double.valueOf("185.24").equals(player.getHeight()) &&
                                new BigDecimal("10.10").equals(player.getSalary())
                ));
    }
}