package es.upm.miw.apaw_practice.domain.services.car;

import es.upm.miw.apaw_practice.domain.models.car.Manufacturer;
import es.upm.miw.apaw_practice.domain.persistence_ports.car.ManufacturerPersistence;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManufacturerService {

    private final ManufacturerPersistence manufacturerPersistence;

    public ManufacturerService(ManufacturerPersistence manufacturerPersistence) {
        this.manufacturerPersistence = manufacturerPersistence;
    }

    public Manufacturer update(String name, Manufacturer manufacturer) {
        return this.manufacturerPersistence.update(name,manufacturer);
    }

    public boolean existsName(String name) {
        return manufacturerPersistence.existName(name);
    }

    public List<String> findOwnerNamesByManufacturerCountry(String country){
        return this.manufacturerPersistence.findOwnerNamesByManufacturerCountry(country);
    }
}
