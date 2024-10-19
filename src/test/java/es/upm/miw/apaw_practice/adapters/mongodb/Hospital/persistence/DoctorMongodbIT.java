package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.persistence;
import es.upm.miw.apaw_practice.adapters.mongodb.Hospital.daos.DoctorRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities.DoctorEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.Hospital.persistence.DoctorPersistenceMongodb;
import es.upm.miw.apaw_practice.domain.models.Hospital.Doctor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class DoctorPersistenceMongodbTest {

    @Mock
    private DoctorRepository doctorRepository;

    @InjectMocks
    private DoctorPersistenceMongodb doctorPersistenceMongodb;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initializes the mocks
    }

    @Test
    void testUpdate() {
        // Arrange
        String dni = "12345678A";
        Doctor doctorToUpdate = new Doctor(dni, "Dr. John Doe", BigDecimal.valueOf(5000));
        DoctorEntity doctorEntity = new DoctorEntity(dni, "Dr. John Doe", BigDecimal.valueOf(5000));

        // Mock the repository to return the same entity when saved
        when(doctorRepository.save(doctorEntity)).thenReturn(doctorEntity);

        // Act
        Doctor updatedDoctor = doctorPersistenceMongodb.update(dni, doctorToUpdate);

        // Assert
        assertEquals("Dr. John Doe", updatedDoctor.getFullname());
        assertEquals(BigDecimal.valueOf(5000), updatedDoctor.getSalary());
    }

    @Test
    void testExistsByDni() {
        // Arrange
        String dni = "12345678A";
        when(doctorRepository.existsByDni(dni)).thenReturn(true);

        // Act
        boolean exists = doctorPersistenceMongodb.existsByDni(dni);

        // Assert
        assertTrue(exists);
    }
}
