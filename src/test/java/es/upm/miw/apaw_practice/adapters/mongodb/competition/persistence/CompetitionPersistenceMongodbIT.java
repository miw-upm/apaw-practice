package es.upm.miw.apaw_practice.adapters.mongodb.competition.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.competition.CompetitionSeederService;
import es.upm.miw.apaw_practice.domain.models.competition.Competition;
import es.upm.miw.apaw_practice.domain.models.competition.PlayerTeam;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class CompetitionPersistenceMongodbIT {

    @Autowired
    private CompetitionPersistenceMongodb competitionPersistenceMongodb;

    @Autowired
    private PlayerTeamPersistenceMongodb playerTeamPersistenceMongodb;

    @Autowired
    private CompetitionSeederService competitionSeederService;

    @Test
    void testReadById() {
        Optional<Competition> competition = this.competitionPersistenceMongodb.readAll()
                .filter(comp -> "Champions League".equals(comp.getNameCompetition()))
                .findFirst();

        assertTrue(competition.isPresent());
    }

    @Test
    void testCompetitionNameByPlayerId() {
        this.competitionSeederService.deleteAll();
        this.competitionSeederService.seedDatabase();

        PlayerTeam playerTeam = this.playerTeamPersistenceMongodb.readAll().toList().get(0);

        List<String> namesCompetitions = this.competitionPersistenceMongodb.competitionNameByPlayerId(playerTeam.getId());

        List<String> resultNamesCompetitions = List.of("Champions League", "El chirincirco");

        assertEquals(resultNamesCompetitions, namesCompetitions);
    }
}
