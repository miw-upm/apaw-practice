package es.upm.miw.apaw.domain.persistenceports.clinic;

import es.upm.miw.apaw.domain.models.clinic.Pet;

import java.util.Optional;
import java.util.UUID;

public interface PetPersistence {
    Optional<Pet> findByMicrochipNumber(Long microchipNumber);
    void addAppointments(Long microchipNumber, UUID appointmentId);
    Pet save(Pet pet);
}