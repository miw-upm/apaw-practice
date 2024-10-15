package es.upm.miw.apaw_practice.adapters.mongodb.veterinary_clinic.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.veterinay_clinic.Animal;
import es.upm.miw.apaw_practice.domain.models.veterinay_clinic.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class EmployeePersistenceMongodbIT {

    @Autowired
    private EmployeePersistenceMongodb employeePersistenceMongodb;

    @Test
    void testReadByName() {
        Employee employee = this.employeePersistenceMongodb.readByName("Paco");
        assertTrue(employee.isDoctor());
        assertEquals(1, employee.getAnimals().size());
        assertTrue(employee.getAnimals().stream()
                .map(Animal::getName)
                .toList()
                .contains("Lara"));
        assertTrue(employee.getAnimals().stream()
                .map(Animal::getAge)
                .toList()
                .contains(10));
    }

    @Test
    void testReadByNameNotFound() {
        assertThrows(NotFoundException.class, () -> this.employeePersistenceMongodb.readByName("Felipe"));
    }
}