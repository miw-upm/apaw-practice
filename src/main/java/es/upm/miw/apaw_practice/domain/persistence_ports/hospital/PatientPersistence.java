package es.upm.miw.apaw_practice.domain.persistence_ports.hospital;

import es.upm.miw.apaw_practice.domain.models.hospital.Patient;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientPersistence {

    void delete(String dni);

    Patient update(String dni, Patient patient);
}
