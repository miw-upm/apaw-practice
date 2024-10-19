package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.persistence;
import es.upm.miw.apaw_practice.adapters.mongodb.Hospital.daos.HospitalRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities.HospitalEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.Hospital.persistence.HospitalPersistenceMongodb;
import es.upm.miw.apaw_practice.domain.models.Hospital.Hospital;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class HospitalPersistenceMongodbTest {

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
        Hospital hospitalToCreate = new Hospital("Central Hospital", "Address 123");
        HospitalEntity hospitalEntity = new HospitalEntity(hospitalToCreate);

        // Mock the repository to return the saved entity
        when(hospitalRepository.save(hospitalEntity)).thenReturn(hospitalEntity);

        // Act
        Hospital createdHospital = hospitalPersistenceMongodb.create(hospitalToCreate);

        // Assert
        assertEquals("Central Hospital", createdHospital.getName());
        assertEquals("Address 123", createdHospital.getAddress());
    }
}
