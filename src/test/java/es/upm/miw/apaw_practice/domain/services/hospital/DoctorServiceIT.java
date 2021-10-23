package es.upm.miw.apaw_practice.domain.services.hospital;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.hospital.Doctor;
import es.upm.miw.apaw_practice.domain.persistence_ports.hospital.DoctorPersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
class DoctorServiceIT {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private DoctorPersistence doctorPersistence;

    @Test
    void testFindSurnamesByDiseaseSeverity() {
        List<Doctor> doctors = this.doctorService.findSurnamesByDiseaseSeverity(Boolean.TRUE)
                .collect(Collectors.toList());
        assertTrue(doctors.size() > 0);
        assertEquals("Lopez", doctors.get(0).getSurname());
    }

}
