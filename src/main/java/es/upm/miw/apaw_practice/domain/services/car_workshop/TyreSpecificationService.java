package es.upm.miw.apaw_practice.domain.services.car_workshop;

import es.upm.miw.apaw_practice.domain.models.car_workshop.TyreSpecsModification;
import es.upm.miw.apaw_practice.domain.persistence_ports.car_workshop.TyreSpecificationPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TyreSpecificationService {

    private final TyreSpecificationPersistence tyreSpecsPersistence;

    @Autowired
    public TyreSpecificationService(TyreSpecificationPersistence tyreSpecsPersistence) {
        this.tyreSpecsPersistence = tyreSpecsPersistence;
    }

    public void updateLoadSpeedIndex(TyreSpecsModification tyreSpecsModification) {
        this.tyreSpecsPersistence.updateLoadSpeedIndex(tyreSpecsModification);
    }
}
