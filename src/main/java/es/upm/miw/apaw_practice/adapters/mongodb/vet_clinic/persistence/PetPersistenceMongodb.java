package es.upm.miw.apaw_practice.adapters.mongodb.vet_clinic.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.vet_clinic.daos.PetRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.vet_clinic.daos.VetRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.vet_clinic.entities.AppointmentEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.vet_clinic.entities.DiagnosisEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.vet_clinic.entities.PetEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.vet_clinic.entities.VetEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.vet_clinic.Diagnosis;
import es.upm.miw.apaw_practice.domain.models.vet_clinic.Pet;
import es.upm.miw.apaw_practice.domain.persistence_ports.vet_clinic.PetPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository("petPersistence")
public class PetPersistenceMongodb implements PetPersistence {
    private final PetRepository petRepository;
    private final VetRepository vetRepository;

    @Autowired
    public PetPersistenceMongodb(PetRepository petRepository, VetRepository vetRepository) {
        this.petRepository = petRepository;
        this.vetRepository = vetRepository;
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

    private List<String> findNicksFromAppointments(List<AppointmentEntity> appointmentEntities) {
        return appointmentEntities.stream()
                .map(AppointmentEntity::getPet)
                .filter(PetEntity::hasCriticalDiagnosis)
                .map(PetEntity::getNick)
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findNicksByVetNumber(Integer vetNumber) {
        VetEntity vetEntity = this.vetRepository.findVetByVetNumber(vetNumber)
                .orElseThrow(() -> new NotFoundException("Vet number: " + vetNumber));
        List<AppointmentEntity> appointmentEntities= vetEntity.getAppointmentEntities();
        return findNicksFromAppointments(appointmentEntities);
    }
}
