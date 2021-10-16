package es.upm.miw.apaw_practice.domain.persistence_ports.car_workshop;

import es.upm.miw.apaw_practice.domain.models.car_workshop.TyreSpecsModification;
import org.springframework.stereotype.Repository;

@Repository
public interface TyreSpecificationPersistence {

    void updateLoadSpeedIndex(TyreSpecsModification tyreSpecsModification);
}
