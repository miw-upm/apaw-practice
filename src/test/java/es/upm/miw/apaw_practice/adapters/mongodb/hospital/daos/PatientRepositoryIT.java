package es.upm.miw.apaw_practice.adapters.mongodb.hospital.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.hospital.entities.PatientEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
class PatientRepositoryIT {

    @Autowired
    private PatientRepository patientRepository;

    @Test
    void testFindByDni() {
        assertTrue(this.patientRepository.findByDni("03457384C").isPresent());
        PatientEntity patientEntity = this.patientRepository.findByDni("03457384C").get();
        assertEquals("03457384C", patientEntity.getDni());
        assertEquals("Male", patientEntity.getGender());
        assertEquals(23, patientEntity.getAge());
    }
}
