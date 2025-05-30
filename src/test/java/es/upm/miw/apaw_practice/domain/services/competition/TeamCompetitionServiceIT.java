package es.upm.miw.apaw_practice.domain.services.competition;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.competition.CompetitionSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.competition.daos.TeamCompetitionRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.competition.entities.TeamCompetitionEntity;
import es.upm.miw.apaw_practice.domain.models.competition.PlayerTeam;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class TeamCompetitionServiceIT {

    @Autowired
    private TeamCompetitionService teamCompetitionService;

    @Autowired
    private TeamCompetitionRepository teamCompetitionRepository;

    @Autowired
    private CompetitionSeederService competitionSeederService;

    @Test
    void testUpdateTeamCompetitionPlayerTeams() {
        Optional<TeamCompetitionEntity> teamCompetitionEntity = this.teamCompetitionRepository.findByNameTeamCompetition("Club nuevo");
        assertTrue(teamCompetitionEntity.isPresent());

        PlayerTeam playerTeam = new PlayerTeam();
        playerTeam.setHeight(182.74);
        playerTeam.setWeight(80.51);
        playerTeam.setSalary(new BigDecimal("12.28"));

        List<PlayerTeam> playerTeams = List.of(playerTeam);

        this.teamCompetitionService.updateTeamCompetitionPlayerTeams(teamCompetitionEntity.get().getId(), playerTeams);

        Optional<TeamCompetitionEntity> updatedTeamCompetitionEntity =
                this.teamCompetitionRepository.findByNameTeamCompetition("Club nuevo");
        assertTrue(updatedTeamCompetitionEntity.isPresent());

        TeamCompetitionEntity updatedEntity = updatedTeamCompetitionEntity.get();
        assertEquals(1, updatedEntity.getPlayerTeamsEntity().size());

        this.competitionSeederService.deleteAll();
        this.competitionSeederService.seedDatabase();

    }
}
