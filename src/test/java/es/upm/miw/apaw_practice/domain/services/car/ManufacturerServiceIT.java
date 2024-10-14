package es.upm.miw.apaw_practice.domain.services.car;


import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.car.daos.ManufacturerRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.car.entities.ManufacturerEntity;
import es.upm.miw.apaw_practice.domain.models.car.Manufacturer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
class ManufacturerServiceIT {

    @Autowired
    ManufacturerService manufacturerService;
    @Autowired
    ManufacturerRepository manufacturerRepository;

    @Test
    void testReadByName() {
        ManufacturerEntity manufacturer = this.manufacturerRepository.findAll().get(0);
        Manufacturer newManufacturerEntity = this.manufacturerService.readByName(manufacturer.getName());
        assertEquals(manufacturer.toManufacturer().getName(), newManufacturerEntity.getName());
    }

    @Test
    void testFindOwnerNamesByManufacturerCountry() {
        assertEquals(1, this.manufacturerService.findOwnerNamesByManufacturerCountry("France").size());
        assertEquals(0, this.manufacturerService.findOwnerNamesByManufacturerCountry("Japan").size());
    }
}
