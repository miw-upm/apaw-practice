package es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.entities.CarEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.entities.OwnerEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.entities.TyreSpecificationEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class CarRepositoryIT {

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private OwnerRepository ownerRepository;

    @Test
    void testFindByLicensePlate(){
        assertTrue(this.carRepository.findByLicensePlate("2222BBB").isPresent());
        CarEntity car = this.carRepository.findByLicensePlate("2222BBB").get();
        assertFalse(car.isRevision());
        OwnerEntity owner = new OwnerEntity("00000000Z", "John Doe");
        assertEquals(owner, car.getOwner());
        TyreSpecificationEntity tyreSpecs = new TyreSpecificationEntity(215, 17, "90T");
        assertEquals(List.of(tyreSpecs), car.getTyreSpecsEntities());
    }

    @Test
    void testFindByOwnerEntity() {
        OwnerEntity owner = this.ownerRepository.findByDni("00000000Z")
                .orElseThrow(()-> new NotFoundException("Owner not found"));
        List<CarEntity> cars = this.carRepository.findByOwner(owner);
        assertEquals(2, cars.size());
    }
}
