package es.upm.miw.apaw_practice.domain.persistence_ports.vet_clinic;

import es.upm.miw.apaw_practice.domain.models.vet_clinic.Diagnosis;
import es.upm.miw.apaw_practice.domain.models.vet_clinic.Pet;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetPersistence {
    void delete(String nick, String owner);

    Pet readByNickAndOwner(String nick, String owner);

    Pet readByChip(Integer chip);

    Pet update(Pet pet, List<Diagnosis> diagnosisList);

    List<String> findNicksByVetNumber(Integer vetNumber);
}
