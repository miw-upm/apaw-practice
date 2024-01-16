package es.upm.miw.apaw_practice.domain.persistence_ports.basketball;

import es.upm.miw.apaw_practice.domain.models.basketball.Player;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerPersistence {
    Player readByEmail(String email);
    Player readBy
    void delete(String email);
}
