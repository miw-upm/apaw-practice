package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities.PatientEntity;
import org.junit.jupiter.api.Test;
import es.upm.miw.apaw_practice.adapters.mongodb.Hospital.daos.PatientRepository;
import es.upm.miw.apaw_practice.TestConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class PatientRepositoryIT {

    @Autowired
    private PatientRepository patientRepository;

    @Test
    void testSaveAndFindById() {
        PatientEntity patient = new PatientEntity("12345678A", "John Doe", LocalDate.of(1990, 1, 1), true);
        patientRepository.save(patient);

        PatientEntity foundPatient = patientRepository.findById("12345678A").orElse(null);
        assertNotNull(foundPatient);
        assertEquals(patient.getFullname(), foundPatient.getFullname());
        assertEquals(patient.getDateOfBirth(), foundPatient.getDateOfBirth());
        assertEquals(patient.getHasInsurance(), foundPatient.getHasInsurance());
    }

    @Test
    void testFindAll() {
        List<PatientEntity> patients = patientRepository.findAll();
        assertNotNull(patients);
        assertTrue(patients.size() >= 0);
    }

    @Test
    void testDelete() {
        PatientEntity patient = new PatientEntity("87654321B", "Jane Smith", LocalDate.of(1985, 5, 20), false);
        patientRepository.save(patient);
        patientRepository.deleteById("87654321B");
        assertFalse(patientRepository.existsById("87654321B"));
    }
}
