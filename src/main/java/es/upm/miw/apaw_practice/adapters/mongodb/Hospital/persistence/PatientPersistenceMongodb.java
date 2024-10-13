package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.persistence;

import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.Hospital.Patient;
import es.upm.miw.apaw_practice.domain.persistence_ports.Hospital.PatientPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository("patientPersistence") // Ensure consistent naming
public class PatientPersistenceMongodb implements PatientPersistence {

    @Autowired
    private PatientRepository patientRepository; // Assuming you have a PatientRepository interface

    @Override
    public Patient create(Patient patient) {
        PatientEntity patientEntity = new PatientEntity(patient); // Assuming you have a PatientEntity class
        return patientRepository.save(patientEntity).toModel(); // Assuming toModel method exists
    }

    @Override
    public Optional<Patient> read(String id) {
        return patientRepository.findById(id).map(PatientEntity::toModel);
    }

    @Override
    public Patient update(Patient patient) {
        // Check if the patient exists before updating
        if (!patientRepository.existsById(patient.getId())) {
            throw new NotFoundException("Patient not found with id: " + patient.getId());
        }
        PatientEntity patientEntity = new PatientEntity(patient);
        return patientRepository.save(patientEntity).toModel();
    }

    @Override
    public void delete(String id) {
        // Check if the patient exists before deleting
        if (!patientRepository.existsById(id)) {
            throw new NotFoundException("Patient not found with id: " + id);
        }
        patientRepository.deleteById(id);
    }

    @Override
    public List<Patient> readAll() {
        return patientRepository.findAll().stream()
                .map(PatientEntity::toModel)
                .collect(Collectors.toList());
    }
}
