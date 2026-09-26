package es.upm.miw.apaw.adapters.in.appointment;

import es.upm.miw.apaw.domain.model.appointment.AppointmentLocation;
import es.upm.miw.apaw.domain.model.appointment.AppointmentLocationPatch;
import es.upm.miw.apaw.domain.services.appointment.AppointmentLocationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(AppointmentLocationResource.APPOINTMENT_LOCATIONS)
@RequiredArgsConstructor
public class AppointmentLocationResource {

    public static final String APPOINTMENT_LOCATIONS = "/appointment-locations";
    public static final String ID = "/{id}";

    private final AppointmentLocationService appointmentLocationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AppointmentLocation create(@Valid @RequestBody AppointmentLocation location) {
        return this.appointmentLocationService.create(location);
    }

    @GetMapping(ID)
    public AppointmentLocation read(@PathVariable UUID id) {
        return this.appointmentLocationService.read(id);
    }

    @PutMapping(ID)
    public AppointmentLocation update(@PathVariable UUID id, @Valid @RequestBody AppointmentLocation location) {
        return this.appointmentLocationService.update(id, location);
    }

    @DeleteMapping(ID)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        this.appointmentLocationService.delete(id);
    }

    @GetMapping
    public List<AppointmentLocation> findAll() {
        return this.appointmentLocationService.findAll();
    }

    @PatchMapping(ID)
    public AppointmentLocation patch(@PathVariable UUID id, @RequestBody AppointmentLocationPatch patch) {
        return this.appointmentLocationService.patch(id, patch);
    }
}
