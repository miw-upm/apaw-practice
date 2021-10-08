package es.upm.miw.apaw_practice.domain.persistence_ports.zoo;

import es.upm.miw.apaw_practice.adapters.mongodb.zoo.entities.ZooEntity;
import es.upm.miw.apaw_practice.domain.models.zoo.CageFumigation;
import org.springframework.stereotype.Repository;

@Repository
public interface CagePersistence {

    void updateNextFumigation(ZooEntity zooEntity, CageFumigation cageFumigation);
}
