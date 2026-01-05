package es.upm.miw.apaw.domain.services.clinic;

import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.clinic.Pet;
import es.upm.miw.apaw.domain.persistenceports.clinic.PetPersistence;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PetService {

    private final PetPersistence petPersistence;

    public PetService(PetPersistence petPersistence) {
        this.petPersistence = petPersistence;
    }

    public Pet readByMicrochip(Long microchipNumber) {
        return this.petPersistence.findByMicrochipNumber(microchipNumber)
                .orElseThrow(() -> new NotFoundException("Pet not found: " + microchipNumber));
    }

    public void assignAppointment(Long microchipNumber, UUID appointmentId) {
        this.petPersistence.addAppointments(microchipNumber, appointmentId);
    }

    public Pet update(Long microchipNumber, Pet updated) {
        Pet existing = this.petPersistence.findByMicrochipNumber(microchipNumber)
                .orElseThrow(() -> new NotFoundException("Pet not found: " + microchipNumber));

        existing.setName(updated.getName());
        existing.setSpecies(updated.getSpecies());
        existing.setGender(updated.getGender());
        existing.setAppointments(updated.getAppointments());
        return this.petPersistence.save(existing);
    }
}