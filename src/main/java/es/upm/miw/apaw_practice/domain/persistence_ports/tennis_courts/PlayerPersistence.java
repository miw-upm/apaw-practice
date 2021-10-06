package es.upm.miw.apaw_practice.domain.persistence_ports.tennis_courts;

import es.upm.miw.apaw_practice.domain.models.tennis_courts.Player;

public interface PlayerPersistence {

    void create(Player player);
}
