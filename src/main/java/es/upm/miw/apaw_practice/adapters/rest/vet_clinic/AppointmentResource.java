package es.upm.miw.apaw_practice.adapters.rest.vet_clinic;

import es.upm.miw.apaw_practice.adapters.rest.LexicalAnalyzer;
import es.upm.miw.apaw_practice.domain.models.vet_clinic.Appointment;
import es.upm.miw.apaw_practice.domain.services.vet_clinic.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping(AppointmentResource.APPOINTMENT)
public class AppointmentResource {
    static final String APPOINTMENT = "/vet-clinic/appointments";
    static final String SEARCH = "/search";

    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentResource(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PatchMapping
    public void updateConsumed(@RequestBody List<Appointment> appointments) {
        this.appointmentService.updateConsumed(appointments.stream());
    }

    @GetMapping(SEARCH)
    public Stream<Appointment> findByConsumed(@RequestParam String q) {
        Boolean consumed = new LexicalAnalyzer().extractWithAssure(q, "consumed", Boolean::new);
        return this.appointmentService.findByConsumed(consumed);
    }

}
