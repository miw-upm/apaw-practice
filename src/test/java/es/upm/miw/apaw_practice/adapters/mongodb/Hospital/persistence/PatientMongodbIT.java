package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.persistence;
import es.upm.miw.apaw_practice.adapters.mongodb.Hospital.daos.PatientRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities.PatientEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.Hospital.persistence.PatientPersistenceMongodb;
import es.upm.miw.apaw_practice.domain.models.Hospital.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PatientMongodbIT {

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientPersistenceMongodb patientPersistenceMongodb;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initializes the mocks
    }

    @Test
    void testDelete() {
        // Arrange
        String dni = "12345678A";

        // Act
        patientPersistenceMongodb.delete(dni);

        // Assert
        verify(patientRepository, times(1)).deleteByDni(dni); // Verify the deleteByDni method is called once
    }

    @Test
    void testUpdateNameSuccessfully() {
        // Arrange
        String dni = "12345678A";
        String newName = "John Doe";
        PatientEntity patientEntity = new PatientEntity(dni, "Old Name");

        // Mock the repository to return the existing patient
        when(patientRepository.findByDni(dni)).thenReturn(Optional.of(patientEntity));
        when(patientRepository.save(any(PatientEntity.class))).thenReturn(new PatientEntity(dni, newName));

        // Act
        Patient updatedPatient = patientPersistenceMongodb.updateName(dni, newName);

        // Assert
        assertEquals(newName, updatedPatient.getFullname()); // Verify the name was updated
        verify(patientRepository, times(1)).save(any(PatientEntity.class)); // Ensure save was called
    }

    @Test
    void testUpdateNamePatientNotFound() {
        // Arrange
        String dni = "12345678A";
        String newName = "John Doe";

        // Mock the repository to return an empty Optional
        when(patientRepository.findByDni(dni)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            patientPersistenceMongodb.updateName(dni, newName);
        });
        assertEquals("Patient not found", exception.getMessage()); // Verify the correct exception message
        verify(patientRepository, times(0)).save(any(PatientEntity.class)); // Ensure save was not called
    }
}

