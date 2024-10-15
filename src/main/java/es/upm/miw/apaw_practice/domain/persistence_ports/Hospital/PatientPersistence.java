package es.upm.miw.apaw_practice.domain.persistence_ports.Hospital;

import es.upm.miw.apaw_practice.domain.models.Hospital.Patient;
import java.util.List;

public interface PatientPersistence {
    List<Patient> findAll();
    void delete(String id);
}
