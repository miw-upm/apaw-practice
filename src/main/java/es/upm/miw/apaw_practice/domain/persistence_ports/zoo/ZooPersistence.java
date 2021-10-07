package es.upm.miw.apaw_practice.domain.persistence_ports.zoo;

import es.upm.miw.apaw_practice.adapters.mongodb.zoo.entities.ZooEntity;
import es.upm.miw.apaw_practice.domain.models.zoo.Zoo;
import org.springframework.stereotype.Repository;

@Repository
public interface ZooPersistence {

    void create(Zoo zoo);

    ZooEntity findById(String id);

    void update(ZooEntity zooEntity);
}
