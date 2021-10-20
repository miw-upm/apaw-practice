package es.upm.miw.apaw_practice.adapters.mongodb.vet_clinic.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.vet_clinic.entities.PetEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class PetRepositoryIT {
    @Autowired
    private PetRepository petRepository;

    @Test
    void findPetByChipTest(){
        assertTrue(this.petRepository.findPetByChip(5555).isPresent());
        PetEntity pet = this.petRepository.findPetByChip(5555).get();
        assertEquals("Chetto", pet.getNick());
        assertEquals(1, pet.getDiagnosisEntities().size());
    }

    @Test
    void findPetByNickAndOwnerTest(){
        assertTrue(this.petRepository.findPetByNickAndOwner("Kairo", "Maria").isPresent());
        PetEntity pet = this.petRepository.findPetByNickAndOwner("Kairo", "Maria").get();
        assertEquals(2222, pet.getChip());
        assertEquals(1, pet.getAge());
    }

    @Test
    void deleteByNickAndOwnerTest() {
        this.petRepository.deleteByNickAndOwner("Neko", "Carmen");
        assertFalse(this.petRepository.findAll().stream()
                .anyMatch(pet -> "Neko".equals(pet.getNick()) &&
                        "Carmen".equals(pet.getOwner())
                ));
    }
}
