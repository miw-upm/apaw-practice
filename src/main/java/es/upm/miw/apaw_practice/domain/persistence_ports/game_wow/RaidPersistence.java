package es.upm.miw.apaw_practice.domain.persistence_ports.game_wow;

import es.upm.miw.apaw_practice.domain.models.game_wow.Raid;
import es.upm.miw.apaw_practice.domain.models.shop.ShoppingCart;

public interface RaidPersistence {
    Raid readById(String id);
    Raid update(Raid raid);
}
