package es.upm.miw.apaw.domain.persistenceports.clinic;

import es.upm.miw.apaw.domain.models.clinic.Appointment;

import java.util.Optional;
import java.util.UUID;

public interface AppointmentPersistence {
    Optional<Appointment> findById(UUID id);
    Appointment save(Appointment appointment);
}