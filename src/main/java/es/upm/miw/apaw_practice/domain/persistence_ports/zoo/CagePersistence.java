package es.upm.miw.apaw_practice.domain.persistence_ports.zoo;

import es.upm.miw.apaw_practice.domain.models.zoo.CageFumigation;
import es.upm.miw.apaw_practice.domain.models.zoo.Zoo;
import org.springframework.stereotype.Repository;

@Repository
public interface CagePersistence {

    void updateNextFumigation(Zoo zoo, CageFumigation cageFumigation);
}
