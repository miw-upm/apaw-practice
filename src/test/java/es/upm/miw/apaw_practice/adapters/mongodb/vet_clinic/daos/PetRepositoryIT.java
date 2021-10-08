package es.upm.miw.apaw_practice.adapters.mongodb.vet_clinic.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.vet_clinic.entities.PetEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class PetRepositoryIT {
    @Autowired
    private PetRepository petRepository;

    @Test
    void findPetByChipTest(){
        assertTrue(this.petRepository.findPetByChip(1111).isPresent());
        PetEntity pet = this.petRepository.findPetByChip(1111).get();
        assertEquals("Neko", pet.getNick());
        assertEquals(2, pet.getDiagnosisEntities().size());
    }

    @Test
    void findPetByNickAndOwnerTest(){
        assertTrue(this.petRepository.findPetByNickAndOwner("Kairo", "Maria").isPresent());
        PetEntity pet = this.petRepository.findPetByNickAndOwner("Kairo", "Maria").get();
        assertEquals(2222, pet.getChip());
        assertEquals(1, pet.getAge());
    }
}
