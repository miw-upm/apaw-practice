package es.upm.miw.apaw_practice.domain.persistence_ports.basketball;

import es.upm.miw.apaw_practice.domain.models.basketball.Player;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerPersistence {
    Player readByEmail(String email);
    String readByBasketId(String basket_id);
    void delete(String email);
}
