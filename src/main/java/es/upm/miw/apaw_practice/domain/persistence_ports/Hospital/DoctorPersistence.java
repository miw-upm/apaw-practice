package es.upm.miw.apaw_practice.domain.persistence_ports.Hospital;

import es.upm.miw.apaw_practice.domain.models.hospital.Doctor; // Ensure this package and class exist
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorPersistence {

    Doctor create(Doctor doctor); // Create a new doctor

    Optional<Doctor> read(String id); // Read a doctor by ID

    Doctor update(Doctor doctor); // Update an existing doctor

    void delete(String id); // Delete a doctor by ID

    List<Doctor> readAll(); // Read all doctors
}
