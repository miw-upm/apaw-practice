package es.upm.miw.apaw_practice.domain.persistence_ports.Hospital;
import es.upm.miw.apaw_practice.domain.persistence_ports.Hospital.PatientPersistence;

public interface PatientPersistence {
    void delete(String dni);

}
