package es.upm.miw.apaw_practice.adapters.mongodb.car.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.car.daos.ManufacturerRepository;
import es.upm.miw.apaw_practice.domain.models.car.Manufacturer;
import es.upm.miw.apaw_practice.domain.persistence_ports.car.ManufacturerPersistence;
import org.springframework.stereotype.Repository;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.car.Manufacturer;
import org.springframework.beans.factory.annotation.Autowired;

@Repository("manufacturerPersistence")
public class ManufacturerPersistenceMongodb implements ManufacturerPersistence {

    private ManufacturerRepository manufacturerRepository;

    @Autowired
    public ManufacturerPersistenceMongodb(ManufacturerRepository manufacturerRepository){
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public Manufacturer readByName(String name){
        return manufacturerRepository
                .findByName(name)
                .orElseThrow(() -> new NotFoundException("Manufacturer name: " + name))
                .toManufacturer();
    }
}
