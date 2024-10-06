package es.upm.miw.apaw_practice.adapters.mongodb.competition.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.competition.PlayerTeam;
import es.upm.miw.apaw_practice.domain.models.competition.TeamCompetition;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@TestConfig
class TeamCompetitionPersistenceMongodbIT {

    @Autowired
    private TeamCompetitionPersistenceMongodb teamCompetitionPersistenceMongodb;

    @Test
    void testReadById() {
        Optional<TeamCompetition> teamCompetition = this.teamCompetitionPersistenceMongodb.readAll()
                .filter(tc -> "Atlético de Madrid".equals(tc.getNameTeamCompetition()))
                .findFirst();
        assertTrue(teamCompetition.isPresent());
    }

    @Test
    void testUpdate() {
        Optional<TeamCompetition> teamCompetition = this.teamCompetitionPersistenceMongodb.readAll()
                .filter(tc -> "Atlético de Madrid".equals(tc.getNameTeamCompetition()))
                .findFirst();
        assertTrue(teamCompetition.isPresent());
        List<PlayerTeam> playerTeams = teamCompetition.get().getPlayerTeams();
        playerTeams.clear();
        PlayerTeam playerTeam = new PlayerTeam();
        playerTeam.setHeight(182.74);
        playerTeam.setWeight(80.51);
        playerTeam.setSalary(new BigDecimal("12.28"));
        playerTeams.add(playerTeam);
        this.teamCompetitionPersistenceMongodb.update(teamCompetition.get());

        Optional<TeamCompetition> newTeamCompetition = this.teamCompetitionPersistenceMongodb.readAll()
                .filter(tc -> "Atlético de Madrid".equals(tc.getNameTeamCompetition()))
                .findFirst();
        assertTrue(newTeamCompetition.isPresent());
        assertEquals(teamCompetition.get().getNameTeamCompetition(), newTeamCompetition.get().getNameTeamCompetition());
        assertEquals(1, newTeamCompetition.get().getPlayerTeams().size());
        assertEquals(182.74, newTeamCompetition.get().getPlayerTeams().get(0).getHeight());

    }
}
