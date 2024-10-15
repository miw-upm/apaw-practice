package es.upm.miw.apaw_practice.domain.services.Hospital;

import es.upm.miw.apaw_practice.domain.exceptions.ConflictException;
import es.upm.miw.apaw_practice.domain.models.Hospital.Hospital;
import es.upm.miw.apaw_practice.domain.persistence_ports.Hospital.HospitalPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalService {

    private final HospitalPersistence hospitalPersistence;

    @Autowired
    public HospitalService(HospitalPersistence hospitalPersistence) {
        this.hospitalPersistence = hospitalPersistence;
    }

    // Method to create a new hospital
    public Hospital create(Hospital hospital) {
        if (this.hospitalPersistence.existsByName(hospital.getName())) {
            throw new ConflictException("Hospital with name '" + hospital.getName() + "' already exists.");
        }
        return this.hospitalPersistence.create(hospital);
    }
}
