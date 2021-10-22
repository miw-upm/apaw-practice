package es.upm.miw.apaw_practice.domain.persistence_ports.hospital;

import es.upm.miw.apaw_practice.domain.models.hospital.Patient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientPersistence {

    void delete(String dni);

    Patient update(String dni, Patient patient);

    List<Patient> findAllWithDiseaseSeverity(Boolean severity);
}
