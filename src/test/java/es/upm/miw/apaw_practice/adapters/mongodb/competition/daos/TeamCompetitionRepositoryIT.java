package es.upm.miw.apaw_practice.adapters.mongodb.competition.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.competition.entities.TeamCompetitionEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
class TeamCompetitionRepositoryIT {
    @Autowired
    private TeamCompetitionRepository teamCompetitionRepository;

    @Test
    void findByName() {
        assertFalse(this.teamCompetitionRepository.findByNameTeamCompetition("Real Madrid").isPresent());
    }

    @Test
    void createAndSave(){
        TeamCompetitionEntity teamCompetitionEntity = this.teamCompetitionRepository.findByNameTeamCompetition("Atlético de Madrid").get();
        assertEquals("Diego Pablo Simeone", teamCompetitionEntity.getCoachName());
        assertEquals(37, teamCompetitionEntity.getNumberCompetitionWon());
    }
}
