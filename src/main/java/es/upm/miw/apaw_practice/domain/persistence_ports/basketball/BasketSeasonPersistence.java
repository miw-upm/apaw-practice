package es.upm.miw.apaw_practice.domain.persistence_ports.basketball;

import es.upm.miw.apaw_practice.domain.models.basketball.BasketSeason;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketSeasonPersistence {
    BasketSeason create(BasketSeason basketSeason);
}
