package es.upm.miw.apaw_practice.domain.services.Hospital;

import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.Hospital.Doctor;
import es.upm.miw.apaw_practice.domain.persistence_ports.Hospital.DoctorPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class DoctorService {

    private final DoctorPersistence doctorPersistence;

    @Autowired
    public DoctorService(DoctorPersistence doctorPersistence) {
        this.doctorPersistence = doctorPersistence;
    }

    public Doctor updateDoctor(String dni, Doctor updatedDoctor) {
       

        return doctorPersistence.update(dni, updatedDoctor);
    }
}
