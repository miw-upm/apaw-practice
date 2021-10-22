package es.upm.miw.apaw_practice.adapters.mongodb.hospital.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.hospital.Patient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
public class PatientPersistenceMongodbIT {

    @Autowired
    private PatientPersistenceMongodb patientPersistenceMongodb;

    @Test
    void testUpdate(){
        Patient patient = new Patient("12345678Z","Female",46,null,null);
        Patient patient2 = this.patientPersistenceMongodb.update("12345678Z", patient);
        assertEquals("12345678Z",patient2.getDni());
        assertEquals("Female",patient2.getGender());
        assertEquals(46,patient2.getAge());
    }

    @Test
    void testFindAllWithDiseaseSeverity(){
        List<Patient> patients = this.patientPersistenceMongodb.findAllWithDiseaseSeverity(Boolean.TRUE);
        assertEquals("03457384C",patients.get(0).getDni());
    }

}
