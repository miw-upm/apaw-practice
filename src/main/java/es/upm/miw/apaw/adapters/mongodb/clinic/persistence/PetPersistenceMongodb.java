package es.upm.miw.apaw.adapters.mongodb.clinic.persistence;

import es.upm.miw.apaw.adapters.mongodb.clinic.daos.PetRepository;
import es.upm.miw.apaw.adapters.mongodb.clinic.entities.PetEntity;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.clinic.Pet;
import es.upm.miw.apaw.domain.persistenceports.clinic.PetPersistence;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("petPersistence")
public class PetPersistenceMongodb implements PetPersistence {

    private final PetRepository petRepository;

    public PetPersistenceMongodb(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public Optional<Pet> findByMicrochipNumber(Long microchipNumber) {
        return this.petRepository.findByMicrochipNumber(microchipNumber)
                .map(PetEntity::toPet);
    }

    @Override
    public void addAppointments(Long microchipNumber, UUID appointmentId) {
        PetEntity petEntity = this.petRepository.findByMicrochipNumber(microchipNumber)
                .orElseThrow(() -> new NotFoundException("Pet not found"));
        if (petEntity.getAppointments() == null) {
            petEntity.setAppointments(new ArrayList<>());
        }
        petEntity.getAppointments().add(appointmentId);
        this.petRepository.save(petEntity);
    }

    @Override
    public Pet save(Pet pet) {
        return this.petRepository.save(new PetEntity(pet)).toPet();
    }

    @Override
    public List<Pet> findByAppointmentsIn(List<UUID> appointments) {
        return this.petRepository.findByAppointmentsIn(appointments).stream()
                .map(PetEntity::toPet)
                .toList();
    }
}