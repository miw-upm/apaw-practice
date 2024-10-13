package es.upm.miw.apaw_practice.domain.persistence_ports.Hospital;

import es.upm.miw.apaw_practice.domain.models.Hospital.Patient; // Ensure this package and class exist
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Stream;

@Repository
public interface PatientPersistence {

    Patient create(Patient patient); // Create a new patient

    Optional<Patient> read(String id); // Read a patient by ID

    Patient update(Patient patient); // Update an existing patient

    void delete(String id); // Delete a patient by ID

    Stream<Patient> readAll(); // Read all patients
}
