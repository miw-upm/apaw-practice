package es.upm.miw.apaw_practice.adapters.mongodb.veterinary_clinic.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.veterinary_clinic.entities.ClinicEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.veterinary_clinic.entities.EmployeeEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
class ClinicRepositoryIT {

    @Autowired
    private ClinicRepository clinicRepository;

    @Test
    void testCreateAndRead() {
        assertTrue(this.clinicRepository.findByName("Veterinary Clinic Happy Life").isPresent());
        ClinicEntity clinic = this.clinicRepository.findByName("Veterinary Clinic Happy Life").get();
        assertEquals("Veterinary Clinic Happy Life", clinic.getName());
        assertEquals("Street San Francisco", clinic.getAddress());
        assertTrue(clinic.getEmployeeEntities().stream()
                .map(EmployeeEntity::getName)
                .collect(Collectors.toList())
                .contains("Paco"));
    }
}