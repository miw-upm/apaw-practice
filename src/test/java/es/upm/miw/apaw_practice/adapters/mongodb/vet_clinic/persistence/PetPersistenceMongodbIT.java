package es.upm.miw.apaw_practice.adapters.mongodb.vet_clinic.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.vet_clinic.daos.PetRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertFalse;

@TestConfig
public class PetPersistenceMongodbIT {

    @Autowired
    private PetPersistenceMongodb petPersistence;
    @Autowired
    private PetRepository petRepository;

    @Test
    void deleteTest() {
        this.petPersistence.delete("Coca", "Pedro");
        assertFalse(this.petRepository.findAll().stream()
                .anyMatch(pet ->
                        "Coca".equals(pet.getNick()) &&
                                "Pedro".equals(pet.getOwner())
                ));
    }
}
