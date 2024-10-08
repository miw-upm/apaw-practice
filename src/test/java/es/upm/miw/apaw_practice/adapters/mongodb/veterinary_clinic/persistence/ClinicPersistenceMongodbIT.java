package es.upm.miw.apaw_practice.adapters.mongodb.veterinary_clinic.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.veterinay_clinic.Clinic;
import es.upm.miw.apaw_practice.domain.models.veterinay_clinic.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
class ClinicPersistenceMongodbIT {

    @Autowired
    private ClinicPersistenceMongodb clinicPersistence;

    @Test
    void testReadByName() {
        Clinic clinic = this.clinicPersistence.readByName("Veterinary Clinic Happy Life");
        assertEquals("Street San Francisco", clinic.getAddress());
        assertEquals(1, clinic.getEmployees().size());
        assertTrue(clinic.getEmployees().stream()
                .map(Employee::getName)
                .collect(Collectors.toList())
                .contains("Paco"));
    }
}