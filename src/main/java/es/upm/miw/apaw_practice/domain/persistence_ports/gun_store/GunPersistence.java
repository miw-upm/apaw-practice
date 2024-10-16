package es.upm.miw.apaw_practice.domain.persistence_ports.gun_store;

import es.upm.miw.apaw_practice.domain.models.gun_store.Gun;
import org.springframework.stereotype.Repository;

@Repository
public interface GunPersistence {
    Gun readByName(String name);
}
