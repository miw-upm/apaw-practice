package es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.daos.OwnerRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.entities.OwnerEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.entities.TyreSpecificationEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.car_workshop.Car;
import es.upm.miw.apaw_practice.domain.models.car_workshop.TyreSpecification;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class CarPersistenceMongodbIT {

    @Autowired
    private CarPersistenceMongodb carPersistence;
    @Autowired
    private OwnerRepository ownerRepository;

    @Test
    void testFindByLicensePlate() {
        String licensePlate = "1111AAA";
        Car car = this.carPersistence.findByLicensePlate(licensePlate);
        assertNotNull(car.getOwner());
        assertTrue(car.isRevision());
        assertEquals("John Doe", car.getOwner().getName());
    }

    @Test
    void testFindByOwnerAndRevision() {
        OwnerEntity owner = this.ownerRepository.findByDni("00000000Z")
                .orElseThrow(() -> new NotFoundException("Owner not found"));
        List<Car> cars = this.carPersistence.findByOwnerAndRevision(owner, true).collect(Collectors.toList());
        assertEquals(1, cars.size());
        assertEquals("1111AAA", cars.get(0).getLicensePlate());
    }

    @Test
    void testFindByTyreSpecifications() {
        Stream<TyreSpecification> tyreSpecs = Stream.of(
                new TyreSpecificationEntity(215, 17, "90T")
                        .toTyreSpecification()
        );
        List<Car> cars = this.carPersistence.findByTyreSpecifications(tyreSpecs)
                .collect(Collectors.toList());
        assertEquals(2, cars.size());
        List<String> licensePlates = cars.stream().map(Car::getLicensePlate)
                .collect(Collectors.toList());
        assertTrue(licensePlates.containsAll(List.of("2222BBB", "3333CCC")));
    }
}
