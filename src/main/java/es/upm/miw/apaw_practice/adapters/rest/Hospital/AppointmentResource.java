package es.upm.miw.apaw_practice.adapters.rest.Hospital;

import es.upm.miw.apaw_practice.domain.models.Hospital.Appointment;
import es.upm.miw.apaw_practice.domain.services.Hospital.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(AppointmentResource.APPOINTMENTS)
public class AppoinmentPersistence {

    static final String APPOINTMENTS = "/hospital/appointments";
    static final String APPOINTMENT_ID = "/{id}";

    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentResource(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    // Método para obtener todas las citas
    @GetMapping
    public List<Appointment> getAllAppointments() {
        return this.appointmentService.findAll();
    }


}

