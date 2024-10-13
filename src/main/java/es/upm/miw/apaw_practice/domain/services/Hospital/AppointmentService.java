package es.upm.miw.apaw_practice.domain.services.hospital;

import es.upm.miw.apaw_practice.domain.models.hospital.Appointment; // Asegúrate de que este paquete y clase existan
import es.upm.miw.apaw_practice.domain.persistence_ports.hospital.AppointmentPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AppointmentService {

    private final AppointmentPersistence appointmentPersistence;

    @Autowired
    public AppointmentService(AppointmentPersistence appointmentPersistence) {
        this.appointmentPersistence = appointmentPersistence;
    }

    public List<Appointment> readAll() {
        Stream<Appointment> appointmentsStream = this.appointmentPersistence.readAll();
        return appointmentsStream.collect(Collectors.toList());
    }

    public Appointment read(String id) {
        return this.appointmentPersistence.read(id); // Método para obtener una cita por ID
    }
}
