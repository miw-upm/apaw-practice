package es.upm.miw.apaw_practice.domain.services.vet_clinic;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.vet_clinic.entities.DiagnosisEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.vet_clinic.Diagnosis;
import es.upm.miw.apaw_practice.domain.models.vet_clinic.Pet;
import es.upm.miw.apaw_practice.domain.persistence_ports.vet_clinic.PetPersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestConfig
public class PetServiceIT {

    @Autowired
    private PetService petService;
    @Autowired
    private PetPersistence petPersistence;

    @Test
    void deleteTest() {
        this.petService.delete("Lilith", "Pedro");
        assertThrows(NotFoundException.class, () ->
                this.petPersistence.readByNickAndOwner("Lilith", "Pedro"));
    }

    @Test
    void updateTest() {
        Pet pet = this.petPersistence.readByChip(2222);
        List<Diagnosis> diagnosisList = List.of(
                new Diagnosis("diagnosis1", "medicine1", false),
                new Diagnosis("diagnosis2", "", false)
        );
        this.petPersistence.update(pet, diagnosisList);
        assertEquals(diagnosisList, pet.getDiagnosis());
    }

    //@Test
    void findNicksByVetNumberTest() {
        List<String> nicks = new ArrayList<>();
        nicks.add("Cooper");
        nicks.add("Makalu");
        assertEquals(nicks, this.petPersistence.findNicksByVetNumber(111));
    }
}
