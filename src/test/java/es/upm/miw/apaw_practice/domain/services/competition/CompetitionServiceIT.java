package es.upm.miw.apaw_practice.domain.services.competition;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.competition.daos.CompetitionRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.competition.entities.CompetitionEntity;
import es.upm.miw.apaw_practice.domain.models.competition.Competition;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class CompetitionServiceIT {

    @Autowired
    private CompetitionService competitionService;

    @Autowired
    private CompetitionRepository competitionRepository;

    @Test
    void testReadById() {
        CompetitionEntity competition = this.competitionRepository.findAll().get(0);

        Competition newCompetitionEntity = this.competitionService.readById(competition.getId());

        assertEquals(competition.toCompetition().getNameCompetition(), newCompetitionEntity.getNameCompetition());
    }
}

