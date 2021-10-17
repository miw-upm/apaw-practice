package es.upm.miw.apaw_practice.domain.persistence_ports.hospital;

import org.springframework.stereotype.Repository;

@Repository
public interface PatientPersistence {

    void delete(String dni);
}
