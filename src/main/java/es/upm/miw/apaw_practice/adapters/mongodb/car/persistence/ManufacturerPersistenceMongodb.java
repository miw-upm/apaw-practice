package es.upm.miw.apaw_practice.adapters.mongodb.car.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.car.daos.CarRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.car.daos.ManufacturerRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.car.entities.CarEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.car.entities.ManufacturerEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.car.entities.OwnerCarEntity;
import es.upm.miw.apaw_practice.domain.models.car.Manufacturer;
import es.upm.miw.apaw_practice.domain.persistence_ports.car.ManufacturerPersistence;
import org.springframework.stereotype.Repository;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Repository("manufacturerPersistence")
public class ManufacturerPersistenceMongodb implements ManufacturerPersistence {

    private ManufacturerRepository manufacturerRepository;

    private CarRepository carRepository;

    @Autowired
    public ManufacturerPersistenceMongodb(ManufacturerRepository manufacturerRepository, CarRepository carRepository){
        this.manufacturerRepository = manufacturerRepository;
        this.carRepository = carRepository;
    }

    @Override
    public Stream<Manufacturer> readAll() {
        return manufacturerRepository
                .findAll()
                .stream()
                .map(ManufacturerEntity::toManufacturer);
    }

    @Override
    public Manufacturer readByName(String name){
        return manufacturerRepository
                .findByName(name)
                .orElseThrow(() -> new NotFoundException("Manufacturer name: " + name))
                .toManufacturer();
    }

    @Override
    public Manufacturer update(String name, Manufacturer manufacturer) {
        ManufacturerEntity manufacturerEntity = manufacturerRepository
                .findByName(name)
                .orElseThrow(() -> new NotFoundException("Customer name:" + name));
        manufacturerEntity.fromManufacturer(manufacturer);
        return manufacturerRepository
                .save(manufacturerEntity)
                .toManufacturer();
    }

    @Override
    public boolean existName(String name) {
        return manufacturerRepository.findByName(name).isPresent();

    }

    @Override
    public List<String> findOwnerNamesByManufacturerCountry(String country) {
        return this.carRepository.findAll().stream()
                .filter(car -> car.getPiecesEntity().stream()
                        .anyMatch(piece -> piece.getManufacturerListEntity().stream()
                                .anyMatch(manufacturer -> manufacturer.getCountry().equals(country))))
                .map(CarEntity::getOwnerEntity)
                .filter(Objects::nonNull)
                .map(OwnerCarEntity::getName)
                .distinct()
                .toList();
    }
}
