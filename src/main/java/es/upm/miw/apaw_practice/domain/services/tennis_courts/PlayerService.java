package es.upm.miw.apaw_practice.domain.services.tennis_courts;

import es.upm.miw.apaw_practice.domain.models.tennis_courts.Player;
import es.upm.miw.apaw_practice.domain.persistence_ports.tennis_courts.PlayerPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    private final PlayerPersistence playerPersistence;

    @Autowired
    public PlayerService(PlayerPersistence playerPersistence){
        this.playerPersistence = playerPersistence;
    }

    public void create(Player player){
        this.playerPersistence.create(player);
    }
}
