package es.upm.miw.apaw_practice.adapters.mongodb.competition.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.competition.entities.CompetitionEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
class CompetitionRepositoryIT {

    @Autowired
    private CompetitionRepository competitionRepository;

    @Test
    void testFindByName() {
        assertTrue(this.competitionRepository.findByNameCompetition("Champions League").isPresent());
        CompetitionEntity competitionEntity = this.competitionRepository.findByNameCompetition("Champions League").get();
        assertEquals(LocalDate.of(2024, 9, 15), competitionEntity.getStartDate());
        assertEquals(LocalDate.of(2025, 6, 23), competitionEntity.getEndDate());
    }
}
