package es.upm.miw.apaw_practice.domain.persistence_ports.gun_store;

import es.upm.miw.apaw_practice.domain.models.gun_store.Accesory;
import org.springframework.stereotype.Repository;


@Repository
public interface AccesoryPersistence {

    Accesory update(Integer accesoryId, Accesory accesory);

    Accesory read(Integer accesoryId);
}
