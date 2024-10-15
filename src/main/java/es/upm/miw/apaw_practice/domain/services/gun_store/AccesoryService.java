package es.upm.miw.apaw_practice.domain.services.gun_store;

import es.upm.miw.apaw_practice.domain.models.gun_store.Accesory;
import es.upm.miw.apaw_practice.domain.models.gun_store.AccesoryPriceUpdating;
import es.upm.miw.apaw_practice.domain.persistence_ports.gun_store.AccesoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccesoryService {

    private final AccesoryPersistence accesoryPersistence;

    @Autowired
    public AccesoryService(AccesoryPersistence accesoryPersistence) {
        this.accesoryPersistence = accesoryPersistence;
    }

    public void updatePrice(AccesoryPriceUpdating accesoryPriceUpdating){
        Accesory accesory = accesoryPersistence.read(accesoryPriceUpdating.getAccesoryId());
        accesory.setPrice(accesoryPriceUpdating.getNewPrice());
        this.accesoryPersistence.update(accesory.getAccesoryId(), accesory);
    }
}
