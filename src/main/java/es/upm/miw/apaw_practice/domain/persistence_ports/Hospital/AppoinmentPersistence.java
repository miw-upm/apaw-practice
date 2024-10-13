package es.upm.miw.apaw_practice.domain.persistence_ports.Hospital;

import es.upm.miw.apaw_practice.domain.models.Hospital.Appointment; // Make sure this package and class exist
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppoinmentPersistence {

    Appointment create(Appointment appointment); // Create a new appointment

    Optional<Appointment> read(String id); // Read an appointment by ID

    Appointment update(Appointment appointment); // Update an existing appointment

    void delete(String id); // Delete an appointment by ID

    List<Appointment> readAll(); // Read all appointments
}

