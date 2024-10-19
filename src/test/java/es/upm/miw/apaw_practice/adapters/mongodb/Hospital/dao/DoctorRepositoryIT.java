package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities.DoctorEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
public class DoctorRepositoryIT {

    @Autowired
    private DoctorRepository doctorRepository;

    @Test
    void testSaveAndFindById() {
        DoctorEntity doctor = new DoctorEntity("12345678A", "Dr. Smith", new BigDecimal("50000.00"));
        doctorRepository.save(doctor);

        DoctorEntity foundDoctor = doctorRepository.findById("12345678A").orElse(null);
        assertNotNull(foundDoctor);
        assertEquals(doctor.getFullname(), foundDoctor.getFullname());
        assertEquals(doctor.getSalary(), foundDoctor.getSalary());
    }

    @Test
    void testFindAll() {
        List<DoctorEntity> doctors = doctorRepository.findAll();
        assertNotNull(doctors);
        assertTrue(doctors.size() >= 0);
    }

    @Test
    void testDelete() {
        DoctorEntity doctor = new DoctorEntity("87654321B", "Dr. Johnson", new BigDecimal("60000.00"));
        doctorRepository.save(doctor);
        doctorRepository.deleteById("87654321B");
        assertFalse(doctorRepository.existsById("87654321B"));
    }
}
