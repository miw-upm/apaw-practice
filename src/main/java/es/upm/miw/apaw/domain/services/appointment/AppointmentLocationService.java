package es.upm.miw.apaw.domain.services.appointment;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.appointment.AppointmentLocation;
import es.upm.miw.apaw.domain.model.appointment.AppointmentLocationPatch;
import es.upm.miw.apaw.domain.ports.out.appointment.AppointmentLocationGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AppointmentLocationService {

    private final AppointmentLocationGateway appointmentLocationGateway;

    public AppointmentLocation create(AppointmentLocation location) {
        if (this.appointmentLocationGateway.existsByName(location.getName())) {
            throw new ConflictException("Appointment location name already exists: " + location.getName());
        }
        location.doDefault();
        return this.appointmentLocationGateway.create(location);
    }

    public AppointmentLocation read(UUID id) {
        return this.appointmentLocationGateway.read(id)
                .orElseThrow(() -> new NotFoundException("Appointment location id not found: " + id));
    }

    public AppointmentLocation update(UUID id, AppointmentLocation location) {
        AppointmentLocation stored = this.read(id);
        if (!stored.getName().equals(location.getName())
                && this.appointmentLocationGateway.existsByName(location.getName())) {
            throw new ConflictException("Appointment location name already exists: " + location.getName());
        }
        stored.setName(location.getName());
        stored.setAddress(location.getAddress());
        stored.setCity(location.getCity());
        stored.setPostalCode(location.getPostalCode());
        stored.setRoom(location.getRoom());
        stored.setFloor(location.getFloor());
        return this.appointmentLocationGateway.update(stored);
    }

    public void delete(UUID id) {
        if (this.appointmentLocationGateway.isReferenced(id)) {
            throw new ConflictException("Appointment location is referenced by an appointment: " + id);
        }
        this.appointmentLocationGateway.delete(id);
    }

    public List<AppointmentLocation> findAll() {
        return this.appointmentLocationGateway.findAll();
    }

    public AppointmentLocation patch(UUID id, AppointmentLocationPatch patch) {
        AppointmentLocation stored = this.read(id);
        if (patch.name() != null) {
            if (!stored.getName().equals(patch.name())
                    && this.appointmentLocationGateway.existsByName(patch.name())) {
                throw new ConflictException("Appointment location name already exists: " + patch.name());
            }
            stored.setName(patch.name());
        }
        if (patch.address() != null) {
            stored.setAddress(patch.address());
        }
        if (patch.city() != null) {
            stored.setCity(patch.city());
        }
        if (patch.postalCode() != null) {
            stored.setPostalCode(patch.postalCode());
        }
        if (patch.room() != null) {
            stored.setRoom(patch.room());
        }
        if (patch.floor() != null) {
            stored.setFloor(patch.floor());
        }
        return this.appointmentLocationGateway.update(stored);
    }
}
