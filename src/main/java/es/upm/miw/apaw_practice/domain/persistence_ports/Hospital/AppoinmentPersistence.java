package es.upm.miw.apaw_practice.domain.persistence_ports.Hospital;

import java.util.List;
import java.util.Optional;
import es.upm.miw.apaw_practice.domain.models.Hospital.Appointment;


public interface AppoinmentPersistence {
    List<Appointment> findAll();
    // Otros métodos que necesites...
}
