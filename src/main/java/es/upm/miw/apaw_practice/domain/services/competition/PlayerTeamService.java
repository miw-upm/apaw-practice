package es.upm.miw.apaw_practice.domain.services.competition;

import es.upm.miw.apaw_practice.domain.persistence_ports.competition.PlayerTeamPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerTeamService {

    private final PlayerTeamPersistence playerTeamPersistence;

    @Autowired
    public PlayerTeamService(PlayerTeamPersistence playerTeamPersistence) {
        this.playerTeamPersistence = playerTeamPersistence;
    }

    public void delete(String id) {
        playerTeamPersistence.delete(id);
    }
}
