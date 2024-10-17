package es.upm.miw.apaw_practice.domain.persistence_ports.Hospital;
import es.upm.miw.apaw_practice.domain.models.Hospital.Patient;
public interface PatientPersistence {
    void delete(String dni);
    Patient updateName(String dni, String name);
}
