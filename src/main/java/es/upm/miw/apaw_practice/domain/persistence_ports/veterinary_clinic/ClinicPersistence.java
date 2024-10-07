package es.upm.miw.apaw_practice.domain.persistence_ports.veterinary_clinic;

import es.upm.miw.apaw_practice.domain.models.veterinay_clinic.Clinic;
import org.springframework.stereotype.Repository;

@Repository
public interface ClinicPersistence {

    Clinic readByName(String name);

    void delete(String name);
}