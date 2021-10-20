package es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.daos.TyreRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.entities.TyreEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.car_workshop.Car;
import es.upm.miw.apaw_practice.domain.models.car_workshop.Tyre;
import es.upm.miw.apaw_practice.domain.models.car_workshop.TyreSpecification;
import es.upm.miw.apaw_practice.domain.persistence_ports.car_workshop.TyrePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository("tyrePersistence")
public class TyrePersistenceMongodb implements TyrePersistence {

    private final TyreRepository tyreRepository;

    @Autowired
    public TyrePersistenceMongodb(TyreRepository tyreRepository) {
        this.tyreRepository = tyreRepository;
    }

    @Override
    public void deleteManufacturer(String manufacturer) {
        this.tyreRepository.deleteByManufacturer(manufacturer);
    }

    @Override
    public Tyre read(String model){
        return this.tyreRepository
                .findByModel(model)
                .orElseThrow(() -> new NotFoundException("Tyre model: " + model))
                .toTyre();
    }

    @Override
    public Stream<String> findDistinctModelByCar(Stream<Car> cars) {
        return cars.flatMap(car -> car.getTyreSpecs().stream())
                .flatMap(tyreSpec -> tyreSpec.getTyres().stream())
                .map(Tyre::getModel)
                .distinct();
    }

    @Override
    public void create(Tyre tyre){
        this.tyreRepository
                .save(new TyreEntity(tyre));
    }

}
