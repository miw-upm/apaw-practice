package es.upm.miw.apaw_practice.adapters.mongodb.vet_clinic.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.vet_clinic.daos.PetRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.vet_clinic.entities.AppointmentEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.vet_clinic.entities.DiagnosisEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.vet_clinic.entities.PetEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.vet_clinic.entities.VetEntity;
import es.upm.miw.apaw_practice.domain.models.vet_clinic.Diagnosis;
import es.upm.miw.apaw_practice.domain.models.vet_clinic.Pet;
import es.upm.miw.apaw_practice.domain.models.vet_clinic.Vet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    @Test
    void updateTest() {
        List<Diagnosis> diagnosisList = List.of(
                new Diagnosis("diagnosis1", "medicine1", false),
                new Diagnosis("diagnosis2", "", false),
                new Diagnosis("diagnosis5", "medicine5", true),
                new Diagnosis("diagnosis6", "", false)
        );
        Pet pet = this.petPersistence.readByChip(2222);
        Pet petUpdated = this.petPersistence.update(pet, diagnosisList);
        assertEquals(diagnosisList, petUpdated.getDiagnosis());
    }

    //@Test
    void findNicksByVetNumberTest() {
        List<String> nicks = new ArrayList<>();
        nicks.add("Cooper");
        nicks.add("Makalu");
        assertEquals(nicks, this.petPersistence.findNicksByVetNumber(111));
    }
}
