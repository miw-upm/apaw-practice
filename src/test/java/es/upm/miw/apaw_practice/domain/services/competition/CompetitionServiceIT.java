package es.upm.miw.apaw_practice.domain.services.competition;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.competition.CompetitionSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.competition.daos.CompetitionRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.competition.entities.CompetitionEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.competition.persistence.PlayerTeamPersistenceMongodb;
import es.upm.miw.apaw_practice.domain.models.competition.Competition;
import es.upm.miw.apaw_practice.domain.models.competition.PlayerTeam;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class CompetitionServiceIT {

    @Autowired
    private CompetitionService competitionService;

    @Autowired
    private CompetitionRepository competitionRepository;

    @Autowired
    private PlayerTeamPersistenceMongodb playerTeamPersistenceMongodb;

    @Autowired
    private CompetitionSeederService competitionSeederService;

    @Test
    void testReadById() {
        CompetitionEntity competition = this.competitionRepository.findAll().get(0);

        Competition newCompetitionEntity = this.competitionService.readById(competition.getId());

        assertEquals(competition.toCompetition().getNameCompetition(), newCompetitionEntity.getNameCompetition());
    }

    @Test
    void testCompetitionNameByPlayerId() {
        this.competitionSeederService.deleteAll();
        this.competitionSeederService.seedDatabase();

        PlayerTeam playerTeam = this.playerTeamPersistenceMongodb.readAll().toList().get(0);

        List<String> namesCompetitions = this.competitionService.competitionNameByPlayerId(playerTeam.getId());

        List<String> resultNamesCompetitions = List.of("Champions League", "El chirincirco");

        assertEquals(resultNamesCompetitions, namesCompetitions);
    }
}

