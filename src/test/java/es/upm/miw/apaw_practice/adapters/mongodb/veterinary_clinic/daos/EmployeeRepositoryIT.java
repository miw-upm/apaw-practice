package es.upm.miw.apaw_practice.adapters.mongodb.veterinary_clinic.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.veterinary_clinic.entities.AnimalEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.veterinary_clinic.entities.EmployeeEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class EmployeeRepositoryIT {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void testCreateAndRead() {
        assertTrue(this.employeeRepository.findByName("Paco").isPresent());
        EmployeeEntity employee = this.employeeRepository.findByName("Paco").get();
        assertEquals("Paco", employee.getName());
        assertTrue(employee.isDoctor());
        assertTrue(employee.getAnimalEntities().stream()
                .map(AnimalEntity::getName)
                .toList()
                .contains("Lara"));
        assertTrue(employee.getAnimalEntities().stream()
                .map(AnimalEntity::getAge)
                .toList()
                .contains(10));
    }

    @Test
    void testDeleteByName() {
        EmployeeEntity employeeEntity = new EmployeeEntity("Lucas", true, List.of());
        this.employeeRepository.save(employeeEntity);
        assertTrue(this.employeeRepository.findByName("Lucas").isPresent());

        int deletedCount = this.employeeRepository.deleteByName("Lucas");
        assertEquals(1, deletedCount);
        assertFalse(this.employeeRepository.findByName("Lucas").isPresent());
    }
}