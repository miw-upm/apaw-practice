package es.upm.miw.apaw_practice.domain.services.Hospital;

import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.Hospital.Doctor;
import es.upm.miw.apaw_practice.domain.persistence_ports.Hospital.DoctorPersistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import es.upm.miw.apaw_practice.TestConfig;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@TestConfig
public class DoctorServiceIT {

    @InjectMocks
    private DoctorService doctorService;

    @Mock
    private DoctorPersistence doctorPersistence;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUpdateDoctorSuccessfully() {
        // Given
        String dni = "12345678A";
        Doctor existingDoctor = new Doctor(1, "Dr. Smith", "Cardiology", new BigDecimal("100000"));
        Doctor updatedDoctor = new Doctor(2, "Dr. John Smith", "Cardiology", new BigDecimal("120000"));

        when(doctorPersistence.update(1, updatedDoctor)).thenReturn(updatedDoctor);

        // When
1
        // Then
        assertNotNull(result);
        assertEquals("Dr. John Smith", result.getName());
        verify(doctorPersistence).update(1, updatedDoctor);
    }

    @Test
    void testUpdateDoctorNotFound() {
        // Given
        String dni = "12345678A";
        Doctor updatedDoctor = new Doctor(1, "Dr. John Smith", "Cardiology", new BigDecimal("120000"));

        when(doctorPersistence.update(1, updatedDoctor)).thenThrow(new NotFoundException("Doctor not found"));

        // When & Then
        NotFoundException exception = assertThrows(NotFoundException.class, () -> doctorService.updateDoctor(dni, updatedDoctor));
        assertEquals("Doctor not found", exception.getMessage());
        verify(doctorPersistence).update(1, updatedDoctor);
    }
}
