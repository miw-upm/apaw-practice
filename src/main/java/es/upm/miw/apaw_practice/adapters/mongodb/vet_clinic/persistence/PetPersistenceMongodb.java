package es.upm.miw.apaw_practice.adapters.mongodb.vet_clinic.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.vet_clinic.daos.PetRepository;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.vet_clinic.Pet;
import es.upm.miw.apaw_practice.domain.persistence_ports.vet_clinic.PetPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
}
