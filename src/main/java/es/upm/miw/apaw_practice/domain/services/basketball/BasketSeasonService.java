package es.upm.miw.apaw_practice.domain.services.basketball;

import es.upm.miw.apaw_practice.domain.models.basketball.BasketSeason;
import es.upm.miw.apaw_practice.domain.persistence_ports.basketball.BasketSeasonPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasketSeasonService {
    private final BasketSeasonPersistence basketSeasonPersistence;

    @Autowired
    public BasketSeasonService(BasketSeasonPersistence basketSeasonPersistence) {
        this.basketSeasonPersistence = basketSeasonPersistence;
    }

    public BasketSeason create(BasketSeason basketSeason) {
        return basketSeasonPersistence.create(basketSeason);
    }
}
