package es.upm.miw.apaw_practice.domain.services.boardgame_cafe;

import es.upm.miw.apaw_practice.domain.persistence_ports.boardgame_cafe.CustomerPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlaySessionService {

    private final CustomerPersistence customerPersistence;

    @Autowired
    public PlaySessionService(CustomerPersistence customerService) {
        this.customerPersistence = customerService;
    }
}
