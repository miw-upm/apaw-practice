package es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.daos.TyreSpecificationRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.entities.TyreSpecificationEntity;
import es.upm.miw.apaw_practice.domain.models.car_workshop.TyreSpecification;
import es.upm.miw.apaw_practice.domain.models.car_workshop.TyreSpecsModification;
import es.upm.miw.apaw_practice.domain.persistence_ports.car_workshop.TyreSpecificationPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository("tyreSpecificationPersistence")
public class TyreSpecificationPersistenceMongodb implements TyreSpecificationPersistence {

    private final TyreSpecificationRepository tyreSpecRepository;

    @Autowired
    public TyreSpecificationPersistenceMongodb(TyreSpecificationRepository tyreSpecRepository) {
        this.tyreSpecRepository = tyreSpecRepository;
    }

    @Override
    public void updateLoadSpeedIndex(TyreSpecsModification tyreSpecsModification) {
        List<TyreSpecificationEntity> tyreSpecs = this.tyreSpecRepository.findAll().stream()
                .filter(tyreSpec -> tyreSpec.getLoadSpeedIndex().equals(tyreSpecsModification.getOldSpec()))
                .collect(Collectors.toList());
        tyreSpecs.forEach(tyreSpec -> tyreSpec.setLoadSpeedIndex(tyreSpecsModification.getNewSpec()));
        this.tyreSpecRepository.saveAll(tyreSpecs);
    }

    @Override
    public Stream<TyreSpecification> findByDiameterGreaterThan(int diameter) {
        return this.tyreSpecRepository.findAll().stream()
                .filter(x -> x.getDiameter() > diameter)
                .map(TyreSpecificationEntity::toTyreSpecification);
    }
}
