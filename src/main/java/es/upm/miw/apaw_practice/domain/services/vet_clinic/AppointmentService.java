package es.upm.miw.apaw_practice.domain.services.vet_clinic;

import es.upm.miw.apaw_practice.domain.models.vet_clinic.Appointment;
import es.upm.miw.apaw_practice.domain.persistence_ports.vet_clinic.AppointmentPersistance;
import es.upm.miw.apaw_practice.domain.persistence_ports.vet_clinic.VetPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class AppointmentService {
    private final AppointmentPersistance appointmentPersistance;
    private final VetPersistence vetPersistence;

    @Autowired
    public AppointmentService(AppointmentPersistance appointmentPersistance, VetPersistence vetPersistence) {
        this.appointmentPersistance = appointmentPersistance;
        this.vetPersistence = vetPersistence;
    }

    public void updateConsumed(Stream<Appointment> appointmentStream) {
        appointmentStream.map(appointmentNewConsumed -> {
            Appointment appointment = this.appointmentPersistance.read(appointmentNewConsumed.getDate(),
                    appointmentNewConsumed.getHour());
            appointment.setConsumed(appointmentNewConsumed.getConsumed());
            return appointment;
        }).forEach(appointment -> this.appointmentPersistance.update(appointment.getDate(), appointment.getHour(), appointment));
    }

    public Stream<Appointment> findByConsumed(Boolean consumed) {
        return this.appointmentPersistance.findByConsumed(consumed);
    }
}
