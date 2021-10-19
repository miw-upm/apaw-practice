package es.upm.miw.apaw_practice.domain.persistence_ports.vet_clinic;

import es.upm.miw.apaw_practice.domain.models.vet_clinic.Pet;
import org.springframework.stereotype.Repository;

@Repository
public interface PetPersistence {
    void delete(String nick, String owner);
    Pet readByNickAndOwner(String nick, String owner);
}
