package es.upm.miw.apaw.domain.ports.out.appointment;

import es.upm.miw.apaw.domain.model.appointment.AppointmentLocation;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AppointmentLocationGateway {
    AppointmentLocation create(AppointmentLocation location);
    Optional<AppointmentLocation> read(UUID id);
    AppointmentLocation update(AppointmentLocation location);
    void delete(UUID id);
    List<AppointmentLocation> findAll();
    boolean existsByName(String name);
    boolean isReferenced(UUID id);
}
