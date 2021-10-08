package es.upm.miw.apaw_practice.domain.persistence_ports.zoo;

import es.upm.miw.apaw_practice.domain.models.zoo.Zoo;
import org.springframework.stereotype.Repository;

@Repository
public interface ZooPersistence {

    void create(Zoo zoo);

    Zoo findById(String id);

    void updateZipCode(String id, String zipCode);
}
