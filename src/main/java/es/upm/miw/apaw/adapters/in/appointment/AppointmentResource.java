package es.upm.miw.apaw.adapters.in.appointment;

import es.upm.miw.apaw.domain.model.appointment.Appointment;
import es.upm.miw.apaw.domain.model.appointment.AppointmentCityReport;
import es.upm.miw.apaw.domain.model.appointment.CreationAppointment;
import es.upm.miw.apaw.domain.services.appointment.AppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(AppointmentResource.APPOINTMENTS)
@RequiredArgsConstructor
public class AppointmentResource {

    public static final String APPOINTMENTS = "/appointments";
    public static final String REPORT = "/report";

    private final AppointmentService appointmentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Appointment create(@Valid @RequestBody CreationAppointment creation) {
        return this.appointmentService.create(creation);
    }

    @GetMapping(REPORT)
    public List<AppointmentCityReport> findCityReport() {
        return this.appointmentService.findCityReport();
    }
}
