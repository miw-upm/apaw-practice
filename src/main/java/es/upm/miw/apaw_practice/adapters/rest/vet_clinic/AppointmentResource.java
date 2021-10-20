package es.upm.miw.apaw_practice.adapters.rest.vet_clinic;

import es.upm.miw.apaw_practice.domain.models.vet_clinic.Appointment;
import es.upm.miw.apaw_practice.domain.services.vet_clinic.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(AppointmentResource.APPOINTMENT)
public class AppointmentResource {
    static final String APPOINTMENT = "/vet-clinic/appointments";
    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentResource(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PatchMapping
    public void updateAppointmentConsumed(@RequestBody List<Appointment> appointments) {
        this.appointmentService.updateConsumed(appointments.stream());
    }
}
