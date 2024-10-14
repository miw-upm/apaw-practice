package es.upm.miw.apaw_practice.adapters.mongodb.car.persistence;


import es.upm.miw.apaw_practice.adapters.mongodb.car.daos.CarRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.car.daos.OwnerCarRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.car.entities.OwnerCarEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.car.entities.PieceEntity;
import es.upm.miw.apaw_practice.domain.persistence_ports.car.OwnerCarPersistence;
import es.upm.miw.apaw_practice.domain.models.car.OwnerCar;
import org.springframework.stereotype.Repository;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

@Repository("ownerCarPersistence")
public class OwnerCarPersistenceMongodb implements OwnerCarPersistence {

    private OwnerCarRepository ownerCarRepository;

    private CarRepository carRepository;

    @Autowired
    public OwnerCarPersistenceMongodb(OwnerCarRepository ownerCarRepository, CarRepository carRepository){
        this.ownerCarRepository = ownerCarRepository;
        this.carRepository = carRepository;
    }

    @Override
    public OwnerCar readByDriverLicense(String driverLicense){
        return ownerCarRepository
                .findByDriverLicense(driverLicense)
                .orElseThrow(() -> new NotFoundException("OwnerClinic Car driverLicense: " + driverLicense))
                .toOwnerCar();
    }

    @Override
    public OwnerCar updateName(String driverLicense, String name) {
        OwnerCarEntity ownerCarEntity = this.ownerCarRepository.findByDriverLicense(driverLicense)
                .orElseThrow(() -> new NotFoundException(" OwnerClinic Car driverLicense: " + driverLicense));
        ownerCarEntity.setName(name);
        return this.ownerCarRepository.save(ownerCarEntity).toOwnerCar();
    }

    @Override
    public BigDecimal getTotalCostByDriverLicense(String driverLicense) {
        return carRepository.findAll()
                .stream()
                .filter(car -> car.getOwnerEntity() != null
                        && car.getOwnerEntity().getDriverLicense().equals(driverLicense))
                .flatMap(car -> car.getPiecesEntity().stream())
                .map(PieceEntity::getCost)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


}