package es.upm.miw.apaw_practice.domain.persistence_ports.Hospital;

import java.util.List;
import java.util.Optional;

public interface AppointmentPersistence {
    List<Appointment> findAll();
    // Otros métodos que necesites...
}
