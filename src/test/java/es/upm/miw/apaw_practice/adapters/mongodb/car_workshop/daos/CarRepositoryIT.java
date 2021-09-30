package es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.entities.CarEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.entities.OwnerEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.entities.TyreSpecificationEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class CarRepositoryIT {

    @Autowired
    private CarRepository carRepository;

    @Test
    void testFindByLicensePlate(){
        assertTrue(this.carRepository.findByLicensePlate("2222BBB").isPresent());
        CarEntity car = this.carRepository.findByLicensePlate("2222BBB").get();
        assertFalse(car.isRevision());
        OwnerEntity owner = new OwnerEntity("00000000Z", "John Doe");
        assertEquals(owner, car.getOwnerEntity());
        TyreSpecificationEntity tyreSpecs = new TyreSpecificationEntity(215, 17, "90T");
        assertEquals(List.of(tyreSpecs), car.getTyreSpecsEntities());
    }
}
