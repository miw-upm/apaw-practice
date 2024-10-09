package es.upm.miw.apaw_practice.adapters.mongodb.car.persistence;


import es.upm.miw.apaw_practice.adapters.mongodb.car.daos.OwnerCarRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.car.entities.OwnerCarEntity;
import es.upm.miw.apaw_practice.domain.persistence_ports.car.OwnerCarPersistence;
import es.upm.miw.apaw_practice.domain.models.car.OwnerCar;
import org.springframework.stereotype.Repository;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

@Repository("ownerCarPersistence")
public class OwnerCarPersistenceMongodb implements OwnerCarPersistence {

    private OwnerCarRepository ownerCarRepository;

    @Autowired
    public OwnerCarPersistenceMongodb(OwnerCarRepository ownerCarRepository){
        this.ownerCarRepository = ownerCarRepository;
    }

    @Override
    public OwnerCar readByDriverLicense(String driverLicense){
        return ownerCarRepository
                .findByDriverLicense(driverLicense)
                .orElseThrow(() -> new NotFoundException("Owner Car driverLicense: " + driverLicense))
                .toOwnerCar();
    }

    @Override
    public OwnerCar updateName(String driverLicense, String name) {
        OwnerCarEntity ownerCarEntity = this.ownerCarRepository.findByDriverLicense(driverLicense)
                .orElseThrow(() -> new NotFoundException(" Owner Car driverLicense: " + driverLicense));
        ownerCarEntity.setName(name);
        return this.ownerCarRepository.save(ownerCarEntity).toOwnerCar();
    }


}
