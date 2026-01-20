package es.upm.miw.apaw.domain.services.clinic;

import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.clinic.Appointment;
import es.upm.miw.apaw.domain.models.clinic.Pet;
import es.upm.miw.apaw.domain.models.clinic.Veterinarian;
import es.upm.miw.apaw.domain.persistenceports.clinic.PetPersistence;
import es.upm.miw.apaw.domain.persistenceports.clinic.VeterinarianPersistence;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PetService {

    private final PetPersistence petPersistence;
    private final VeterinarianPersistence veterinarianPersistence;

    public PetService(PetPersistence petPersistence, VeterinarianPersistence veterinarianPersistence) {
        this.petPersistence = petPersistence;
        this.veterinarianPersistence = veterinarianPersistence;
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

    public List<Long> findMicrochipNumbersByLicenseNumber(Long licenseNumber) {
        Veterinarian veterinarian = this.veterinarianPersistence.findByLicenseNumber(licenseNumber)
                .orElseThrow(() -> new NotFoundException("Veterinarian not found with licenseNumber: " + licenseNumber));

        return this.petPersistence.findByAppointmentsIn(
                        veterinarian.getAppointments().stream().map(Appointment::getId).toList()).stream()
                .map(Pet::getMicrochipNumber)
                .distinct()
                .collect(Collectors.toList());
    }
}