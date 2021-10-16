package es.upm.miw.apaw_practice.domain.persistence_ports.vet_clinic;

import es.upm.miw.apaw_practice.domain.models.vet_clinic.Vet;
import org.springframework.stereotype.Repository;

@Repository
public interface VetPersistence {
    Vet create(Vet vet);
    boolean existVetNumber(Integer vetNumber);
}
