package es.upm.miw.apaw_practice.adapters.rest.competition;

import es.upm.miw.apaw_practice.domain.services.competition.PlayerTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(PlayerTeamResource.PLAYER_TEAM)
public class PlayerTeamResource {

    static final String PLAYER_TEAM = "/competition/playerTeam";
    static final String ID_ID = "/{id}";

    private final PlayerTeamService playerTeamService;

    @Autowired
    public PlayerTeamResource(PlayerTeamService playerTeamService) {
        this.playerTeamService = playerTeamService;
    }

    @DeleteMapping(ID_ID)
    public void delete(@PathVariable String id) {
        this.playerTeamService.delete(id);
    }

}
