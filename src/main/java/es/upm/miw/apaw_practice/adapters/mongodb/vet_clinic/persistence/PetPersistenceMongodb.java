package es.upm.miw.apaw_practice.adapters.mongodb.vet_clinic.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.vet_clinic.daos.PetRepository;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.vet_clinic.Diagnosis;
import es.upm.miw.apaw_practice.domain.models.vet_clinic.Pet;
import es.upm.miw.apaw_practice.domain.persistence_ports.vet_clinic.PetPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("petPersistence")
public class PetPersistenceMongodb implements PetPersistence {
    private final PetRepository petRepository;

    @Autowired
    public PetPersistenceMongodb(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public void delete(String nick, String owner) {
        this.petRepository.deleteByNickAndOwner(nick, owner);
    }

    @Override
    public Pet readByNickAndOwner(String nick, String owner) {
        return this.petRepository
                .findPetByNickAndOwner(nick, owner)
                .orElseThrow(() -> new NotFoundException("Pet nick: " + nick +
                        ", and owner: " + owner))
                .toPet();
    }

    @Override
    public Pet readByChip(Integer chip) {
        return this.petRepository
                .findPetByChip(chip)
                .orElseThrow(() -> new NotFoundException("Pet chip: " + chip))
                .toPet();
    }

    @Override
    public Pet update(Pet pet, List<Diagnosis> diagnosisList) {
        pet.setDiagnosis(diagnosisList);
        return pet;
    }

}
