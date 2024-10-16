package es.upm.miw.apaw_practice.domain.persistence_ports.Hospital;

import es.upm.miw.apaw_practice.domain.models.Hospital.Doctor;

public interface DoctorPersistence {
    Doctor update(String dni, Doctor doctor);
}
