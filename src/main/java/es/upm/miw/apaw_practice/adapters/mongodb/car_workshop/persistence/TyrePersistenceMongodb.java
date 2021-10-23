package es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.daos.TyreRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.daos.TyreSpecificationRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.entities.TyreEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.entities.TyreSpecificationEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.car_workshop.Car;
import es.upm.miw.apaw_practice.domain.models.car_workshop.Tyre;
import es.upm.miw.apaw_practice.domain.models.car_workshop.TyreSpecification;
import es.upm.miw.apaw_practice.domain.persistence_ports.car_workshop.TyrePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository("tyrePersistence")
public class TyrePersistenceMongodb implements TyrePersistence {

    private final TyreRepository tyreRepository;
    private final TyreSpecificationRepository tyreSpecificationRepository;

    @Autowired
    public TyrePersistenceMongodb(TyreRepository tyreRepository, TyreSpecificationRepository tyreSpecificationRepository) {
        this.tyreRepository = tyreRepository;
        this.tyreSpecificationRepository = tyreSpecificationRepository;
    }

    @Override
    public void deleteManufacturer(String manufacturer) {
        this.tyreRepository.deleteByManufacturer(manufacturer);
    }

    @Override
    public Tyre read(String model) {
        return this.tyreRepository
                .findByModel(model)
                .orElseThrow(() -> new NotFoundException("Tyre model: " + model))
                .toTyre();
    }

    @Override
    public Stream<String> findDistinctModelByCar(Stream<Car> cars) {
        List<TyreSpecification> tyreSpecs = cars
                .flatMap(car -> car.getTyreSpecs().stream())
                .collect(Collectors.toList());
        List<Tyre> tyres = findTyresFromTyreSpecification(tyreSpecs);
        return tyres.stream().map(Tyre::getModel).distinct();
    }

    private List<Tyre> findTyresFromTyreSpecification(List<TyreSpecification> tyreSpecs) {
        List<Tyre> tyres = new ArrayList<>();
        for (TyreSpecification tyreSpecification : tyreSpecs) {
            if (tyreSpecification.getTyres() != null) {
                tyres.addAll(tyreSpecs.stream()
                        .flatMap(tyreSpec -> tyreSpec.getTyres().stream())
                        .collect(Collectors.toList()));
            } else {
                Optional<TyreSpecificationEntity> tyreSpecEntity = this.tyreSpecificationRepository.findAll().stream()
                        .filter(x -> x.toTyreSpecification().equals(tyreSpecification)).findFirst();
                tyreSpecEntity.ifPresent(tyreSpecificationEntity -> tyres.addAll(this.tyreRepository.findByTyreSpecsEntity(tyreSpecificationEntity)));
            }
        }
        return tyres;
    }

    @Override
    public void create(Tyre tyre) {
        this.tyreRepository
                .save(new TyreEntity(tyre));
    }

}
