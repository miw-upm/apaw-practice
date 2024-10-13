package es.upm.miw.apaw_practice.domain.services.basketball;

import es.upm.miw.apaw_practice.domain.models.basketball.BasketPlayer;
import es.upm.miw.apaw_practice.domain.persistence_ports.basketball.BasketPlayerPersistence;
import org.springframework.stereotype.Service;

@Service
public class BasketPlayerService {

    private final BasketPlayerPersistence basketPlayerPersistence;

    public BasketPlayerService(BasketPlayerPersistence basketPlayerPersistence) {
        this.basketPlayerPersistence = basketPlayerPersistence;
    }

    public BasketPlayer findByDni(String dni) {
        return this.basketPlayerPersistence.findByDni(dni);
    }
}
