package es.upm.miw.apaw_practice.domain.services.vet_clinic;

import es.upm.miw.apaw_practice.domain.models.vet_clinic.Appointment;
import es.upm.miw.apaw_practice.domain.persistence_ports.vet_clinic.AppointmentPersistance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class AppointmentService {
    private final AppointmentPersistance appointmentPersistance;

    @Autowired
    public AppointmentService(AppointmentPersistance appointmentPersistance) {
        this.appointmentPersistance = appointmentPersistance;
    }

    public void updateConsumed(Stream<Appointment> appointmentStream) {
        appointmentStream.map(appointmentNewConsumed -> {
            Appointment appointment = this.appointmentPersistance.read(appointmentNewConsumed.getDate(),
                    appointmentNewConsumed.getHour());
            appointment.setConsumed(appointmentNewConsumed.getConsumed());
            return appointment;
        }).forEach(appointment -> this.appointmentPersistance.update(appointment.getDate(), appointment.getHour(), appointment));
    }
}
