package es.upm.miw.apaw_practice.adapters.mongodb.veterinary_clinic.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.veterinay_clinic.Clinic;
import es.upm.miw.apaw_practice.domain.models.veterinay_clinic.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

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
                .toList()
                .contains("Paco"));
    }

    @Test
    void testReadByNameNotFound() {
        assertThrows(NotFoundException.class, () -> this.clinicPersistence.readByName("Happy Heal"));
    }

    @Test
    void testFindByOwnerNameSumAge(){
        BigDecimal result = this.clinicPersistence.findByOwnerNameSumAge
                        ("Veterinary Clinic Happy Life", "Marcos")
                .block();
        assertEquals(BigDecimal.valueOf(10), result);
    }
}