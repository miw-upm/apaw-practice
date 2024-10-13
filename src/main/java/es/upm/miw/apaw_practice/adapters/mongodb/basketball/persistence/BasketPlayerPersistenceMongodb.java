package es.upm.miw.apaw_practice.adapters.mongodb.basketball.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.basketball.daos.BasketPlayerRepository;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.basketball.BasketPlayer;
import es.upm.miw.apaw_practice.domain.persistence_ports.basketball.BasketPlayerPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("basketPlayerPersistence")
public class BasketPlayerPersistenceMongodb implements BasketPlayerPersistence {

    private final BasketPlayerRepository basketPlayerRepository;

    @Autowired
    public BasketPlayerPersistenceMongodb(BasketPlayerRepository basketPlayerRepository) {
        this.basketPlayerRepository = basketPlayerRepository;
    }

    @Override
    public BasketPlayer findByDni(String dni) {
        return this.basketPlayerRepository.findByDni(dni)
                .orElseThrow(() -> new NotFoundException(" BasketPlayer dni: " + dni))
                .toBasketPlayer();
    }
}
