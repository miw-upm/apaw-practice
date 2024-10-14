package es.upm.miw.apaw_practice.domain.services.Hospital;

import es.upm.miw.apaw_practice.domain.models.Hospital.Appointment;
import es.upm.miw.apaw_practice.domain.persistence_ports.Hospital.AppointmentPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentPersistence appointmentPersistence;

    @Autowired
    public AppointmentService(AppointmentPersistence appointmentPersistence) {
        this.appointmentPersistence = appointmentPersistence;
    }

    // Método para obtener todas las citas
    public List<Appointment> getAllAppointments() {
        return this.appointmentPersistence.findAll();
    }


}
