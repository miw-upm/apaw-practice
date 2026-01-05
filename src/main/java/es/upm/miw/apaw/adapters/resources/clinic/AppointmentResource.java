package es.upm.miw.apaw.adapters.resources.clinic;

import es.upm.miw.apaw.domain.models.clinic.Appointment;
import es.upm.miw.apaw.domain.services.clinic.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping(AppointmentResource.APPOINTMENTS)
public class AppointmentResource {

    public static final String APPOINTMENTS = "/clinic/appointments";
    public static final String ID = "/{id}";

    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentResource(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Appointment create(
            @Valid @RequestBody Appointment appointment,
            @RequestParam(name = "veterinarian-license") Long licenseVeterinarian,
            @RequestParam(name = "pet-microchip") Long petMicrochip
    ) {
        return this.appointmentService.create(appointment, licenseVeterinarian, petMicrochip);
    }

    @PatchMapping(ID)
    public Appointment updateAppointmentDate(@PathVariable UUID id, @RequestParam LocalDateTime newDate) {
        return this.appointmentService.updateAppointmentDate(id, newDate);
    }
}