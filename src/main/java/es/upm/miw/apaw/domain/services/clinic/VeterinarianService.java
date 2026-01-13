package es.upm.miw.apaw.domain.services.clinic;

import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.clinic.Veterinarian;
import es.upm.miw.apaw.domain.persistenceports.clinic.VeterinarianPersistence;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class VeterinarianService {

    private final VeterinarianPersistence veterinarianPersistence;

    public VeterinarianService(VeterinarianPersistence veterinarianPersistence) {
        this.veterinarianPersistence = veterinarianPersistence;
    }

    public Veterinarian readByLicense(Long licenseNumber) {
        return this.veterinarianPersistence.findByLicenseNumber(licenseNumber)
                .orElseThrow(() -> new NotFoundException("Veterinarian not found: " + licenseNumber));
    }

    public void deleteByLicense(Long licenseNumber) {
        Veterinarian veterinarian = this.readByLicense(licenseNumber);
        this.veterinarianPersistence.delete(veterinarian);
    }

    public void assignAppointment(Long licenceVeterinarian, UUID appointmentId) {
        this.veterinarianPersistence.addAppointments(licenceVeterinarian, appointmentId);
    }

    public List<Veterinarian> findByAppointmentIds(List<UUID> appointmentId) {
        return this.veterinarianPersistence.findByAppointmentIds(appointmentId);
    }

}