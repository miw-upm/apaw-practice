package es.upm.miw.apaw_practice.domain.persistence_ports.basketball;

import es.upm.miw.apaw_practice.domain.models.basketball.BasketPlayer;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketPlayerPersistence {

    BasketPlayer findByDni(String dni);
}
