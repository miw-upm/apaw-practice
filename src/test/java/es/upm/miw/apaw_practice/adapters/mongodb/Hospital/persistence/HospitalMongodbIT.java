package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.Hospital.daos.HospitalRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities.HospitalEntity;
import es.upm.miw.apaw_practice.domain.models.Hospital.Hospital;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
        String name = "Central Hospital";
        String address = "Address 123";
        String phoneNumber = "123456789";
        int bedCount = 100;

        // Update the Hospital constructor as per its definition
        Hospital hospitalToCreate = new Hospital(name, address, phoneNumber, bedCount, new ArrayList<>(), new ArrayList<>());
        HospitalEntity hospitalEntity = new HospitalEntity(hospitalToCreate);

        // Mock the repository to return the saved entity
        when(hospitalRepository.save(hospitalEntity)).thenReturn(hospitalEntity);

        // Act
        Hospital createdHospital = hospitalPersistenceMongodb.create(hospitalToCreate);

        // Assert
        assertEquals(name, createdHospital.getName());
        assertEquals(address, createdHospital.getAddress());
    }
}
