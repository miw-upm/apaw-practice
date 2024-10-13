package es.upm.miw.apaw_practice.domain.persistence_ports.hospital;

import es.upm.miw.apaw_practice.domain.models.hospital.Appointment; // Asegúrate de que este paquete y clase existan
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface AppointmentPersistence {

    Appointment read(String id); // Método para leer una cita específica por ID, si es necesario
}
