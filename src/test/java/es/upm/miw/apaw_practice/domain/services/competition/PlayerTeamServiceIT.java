package es.upm.miw.apaw_practice.domain.services.competition;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.competition.PlayerTeam;
import es.upm.miw.apaw_practice.domain.persistence_ports.competition.PlayerTeamPersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;

@TestConfig
public class PlayerTeamServiceIT {

    @Autowired
    private PlayerTeamService playerTeamService;

    @Autowired
    private PlayerTeamPersistence playerTeamPersistence;

    @Test
    void delete(){
        List<PlayerTeam> playerTeams = playerTeamPersistence.readAll().toList();
        int playerTeamsSize = playerTeams.size();
        assertEquals(playerTeamsSize, playerTeams.size());
        this.playerTeamService.delete(playerTeams.get(0).getId());
        assertEquals(playerTeamsSize - 1, playerTeamPersistence.readAll().toList().size());
    }
}
