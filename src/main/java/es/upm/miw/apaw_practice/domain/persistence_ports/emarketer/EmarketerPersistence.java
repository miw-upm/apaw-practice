package es.upm.miw.apaw_practice.domain.persistence_ports.emarketer;

import org.springframework.stereotype.Repository;


@Repository
public interface EmarketerPersistence {

    void delete(String name);

}
