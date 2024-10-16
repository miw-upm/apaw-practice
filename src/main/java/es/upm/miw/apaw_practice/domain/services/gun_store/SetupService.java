package es.upm.miw.apaw_practice.domain.services.gun_store;

import es.upm.miw.apaw_practice.domain.models.gun_store.Setup;
import es.upm.miw.apaw_practice.domain.persistence_ports.gun_store.SetupPersistence;
import org.springframework.stereotype.Service;

@Service
public class SetupService {

    private final SetupPersistence setupPersistence;

    public SetupService(SetupPersistence setupPersistence) {
        this.setupPersistence = setupPersistence;
    }

    public void delete(Integer id) {
        this.setupPersistence.delete(id);
    }

    public Setup read(Integer id) {
        return this.setupPersistence.read(id);
    }
}
