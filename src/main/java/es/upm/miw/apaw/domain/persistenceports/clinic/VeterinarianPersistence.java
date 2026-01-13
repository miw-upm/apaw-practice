package es.upm.miw.apaw.domain.persistenceports.clinic;

import es.upm.miw.apaw.domain.models.clinic.Veterinarian;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VeterinarianPersistence {
    Optional<Veterinarian> findByLicenseNumber(Long licenseNumber);
    void delete(Veterinarian veterinarian);
    void addAppointments(Long licenseNumber, UUID appointmentId);
    List<Veterinarian> findByAppointmentIds(List<UUID> appointmentId);
}