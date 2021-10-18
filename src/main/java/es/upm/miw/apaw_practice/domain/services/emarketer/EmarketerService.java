package es.upm.miw.apaw_practice.domain.services.emarketer;

import es.upm.miw.apaw_practice.domain.persistence_ports.emarketer.EmarketerPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmarketerService {

    private final EmarketerPersistence emarketerPersistence;

    @Autowired
    public EmarketerService(EmarketerPersistence emarketerPersistence) {
        this.emarketerPersistence = emarketerPersistence;
    }

    public void delete(String name) {
        this.emarketerPersistence.delete(name);
    }

}
