package es.upm.miw.apaw_practice.domain.persistence_ports.gun_store;

import es.upm.miw.apaw_practice.domain.models.gun_store.Setup;
import org.springframework.stereotype.Repository;

@Repository
public interface SetupPersistence {
    void delete(Integer id);

    Setup read(Integer id);
}
