package es.upm.miw.apaw_practice.adapters.rest.competition;

import es.upm.miw.apaw_practice.domain.models.competition.PlayerTeam;
import es.upm.miw.apaw_practice.domain.models.competition.TeamCompetition;
import es.upm.miw.apaw_practice.domain.services.competition.TeamCompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(TeamCompetitionResource.TEAM_COMPETITION)
public class TeamCompetitionResource {

    static final String TEAM_COMPETITION = "/competition/team-competition";
    static final String ID_ID = "/{id}";
    static final String ARTICLE_ITEMS = "/player-teams";

    private final TeamCompetitionService teamCompetitionService;

    @Autowired
    public TeamCompetitionResource(TeamCompetitionService teamCompetitionService) {
        this.teamCompetitionService = teamCompetitionService;
    }

    @PutMapping(ID_ID + ARTICLE_ITEMS)
    public TeamCompetition updatePlayersTeam(@PathVariable String id, @RequestBody List<PlayerTeam> playerTeamList) {
        return this.teamCompetitionService.updateTeamCompetitionPlayerTeams(id, playerTeamList);
    }
}
