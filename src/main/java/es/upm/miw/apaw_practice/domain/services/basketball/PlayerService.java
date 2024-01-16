package es.upm.miw.apaw_practice.domain.services.basketball;

import es.upm.miw.apaw_practice.domain.persistence_ports.basketball.PlayerPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {
    private final PlayerPersistence playerPersistence;
    @Autowired
    public PlayerService(PlayerPersistence playerPersistence) {
        this.playerPersistence = playerPersistence;
    }
    public void delete(String email) {
        this.playerPersistence.delete(email);
    }

    public String read(String basket_id){
        return this.playerPersistence.readByBasketId(basket_id);
    }
}
