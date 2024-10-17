package es.upm.miw.apaw_practice.domain.persistence_ports.basketball;

import es.upm.miw.apaw_practice.domain.models.basketball.BasketMatch;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketMatchPersistence {
    void delete(Integer id);

    BasketMatch updateAddress(Integer matchId, String address);
}
