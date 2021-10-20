package es.upm.miw.apaw_practice.domain.services.vet_clinic;

import es.upm.miw.apaw_practice.domain.models.vet_clinic.Diagnosis;
import es.upm.miw.apaw_practice.domain.models.vet_clinic.Pet;
import es.upm.miw.apaw_practice.domain.persistence_ports.vet_clinic.PetPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {
    private final PetPersistence petPersistence;

    @Autowired
    public PetService(PetPersistence petPersistence) {
        this.petPersistence = petPersistence;
    }

    public void delete(String nick, String owner) {
        this.petPersistence.delete(nick, owner);
    }

    public Pet updateDiagnosis(Integer chip, List<Diagnosis> diagnosisList) {
        Pet pet = this.petPersistence.readByChip(chip);
        return this.petPersistence.update(pet, diagnosisList);
    }
}
