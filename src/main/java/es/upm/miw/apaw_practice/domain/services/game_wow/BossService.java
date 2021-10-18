package es.upm.miw.apaw_practice.domain.services.game_wow;

import es.upm.miw.apaw_practice.domain.persistence_ports.game_wow.BossPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BossService {

    private BossPersistence bossPersistence;
    @Autowired
    public BossService(BossPersistence bossPersistence) {
        this.bossPersistence = bossPersistence;
    }

    public void delete(String description,String effort) {
        this.bossPersistence.delete(description,effort);
    }
}
