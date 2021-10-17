package es.upm.miw.apaw_practice.adapters.mongodb.emarketer.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.emarketer.daos.EmarketerRepository;
import es.upm.miw.apaw_practice.domain.persistence_ports.emarketer.EmarketerPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("emarketerPersistence")
public class EmarketerPersistenceMongodb implements EmarketerPersistence {

    private final EmarketerRepository emarkterRepository;

    @Autowired
    public EmarketerPersistenceMongodb(EmarketerRepository emarkterRepository) {
        this.emarkterRepository = emarkterRepository;
    }

    @Override
    public void delete(String name) {
        this.emarkterRepository.deleteByName(name);
    }

}
