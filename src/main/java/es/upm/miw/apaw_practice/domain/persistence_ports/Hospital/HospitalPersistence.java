package es.upm.miw.apaw_practice.domain.persistence_ports.hospital;

import es.upm.miw.apaw_practice.domain.models.hospital.Hospital; // Ensure this package and class exist
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Stream;

@Repository
public interface HospitalPersistence {

    Hospital create(Hospital hospital); // Create a new hospital

    Optional<Hospital> read(String id); // Read a hospital by ID

    Hospital update(Hospital hospital); // Update an existing hospital

    void delete(String id); // Delete a hospital by ID

    Stream<Hospital> readAll(); // Read all hospitals
}
