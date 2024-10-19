package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.Hospital.daos.HospitalRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities.HospitalEntity;
import es.upm.miw.apaw_practice.domain.models.Hospital.Hospital;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList; // Import for ArrayList
import java.util.List; // Import for List

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class HospitalMongodbIT {

    @Mock
    private HospitalRepository hospitalRepository;

    @InjectMocks
    private HospitalPersistenceMongodb hospitalPersistenceMongodb;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initializes the mocks
    }

    @Test
    void testExistsByName() {
        // Arrange
        String hospitalName = "Central Hospital";
        when(hospitalRepository.existsByName(hospitalName)).thenReturn(true);

        // Act
        boolean exists = hospitalPersistenceMongodb.existsByName(hospitalName);

        // Assert
        assertTrue(exists);
    }

    @Test
    void testCreate() {
        // Arrange
        String id = "1"; // Assuming you want to set an ID
        String name = "Central Hospital";
        String location = "Address 123";
        Integer capacity = 100; // Adjusted to Integer type
        List<Doctor> doctors = new ArrayList<>(); // Assuming you have a Doctor model
        List<Patient> patients = new ArrayList<>(); // Assuming you have a Patient model

        // Update the Hospital constructor as per its definition
        Hospital hospitalToCreate = new Hospital(name, location, capacity, doctors, patients);
        HospitalEntity hospitalEntity = new HospitalEntity(id, name, location, capacity, doctors, patients); // Updated to match constructor

        // Mock the repository to return the saved entity
        when(hospitalRepository.save(hospitalEntity)).thenReturn(hospitalEntity);

        // Act
        Hospital createdHospital = hospitalPersistenceMongodb.create(hospitalToCreate);

        // Assert
        assertEquals(name, createdHospital.getName());
        assertEquals(location, createdHospital.getAddress()); // Ensure this method exists in Hospital
    }
}
