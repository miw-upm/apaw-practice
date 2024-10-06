package es.upm.miw.apaw_practice.domain.services.competition;

import es.upm.miw.apaw_practice.domain.models.competition.PlayerTeam;
import es.upm.miw.apaw_practice.domain.models.competition.TeamCompetition;
import es.upm.miw.apaw_practice.domain.persistence_ports.competition.TeamCompetitionPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamCompetitionService {

    private final TeamCompetitionPersistence teamCompetitionPersistence;

    @Autowired
    public TeamCompetitionService(TeamCompetitionPersistence teamCompetitionPersistence) {
        this.teamCompetitionPersistence = teamCompetitionPersistence;
    }

    public TeamCompetition updateTeamCompetitionPlayerTeams(String id, List<PlayerTeam> playerTeams) {
        TeamCompetition teamCompetition = this.teamCompetitionPersistence.readById(id);
        teamCompetition.setPlayerTeams(playerTeams);
        return this.teamCompetitionPersistence.update(teamCompetition);
    }

}
