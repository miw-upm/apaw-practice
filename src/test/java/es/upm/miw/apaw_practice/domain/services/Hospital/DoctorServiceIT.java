package es.upm.miw.apaw_practice.domain.services.Hospital;

import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.Hospital.Doctor;
import es.upm.miw.apaw_practice.domain.persistence_ports.Hospital.DoctorPersistence;

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

    private final String dniA = "12345678A";



    @Test
    void testUpdateDoctorSuccessfully() {
        // Given
        Doctor existingDoctor = new Doctor(dniA, "Dr. Smith", new BigDecimal("100000")); // Updated constructor
        Doctor updatedDoctor = new Doctor(dniA, "Dr. John Smith", new BigDecimal("120000")); // Updated constructor

        when(doctorPersistence.update(dniA, updatedDoctor)).thenReturn(updatedDoctor);

        // When
        Doctor result = doctorService.updateDoctor(dniA, updatedDoctor);

        // Then
        assertNotNull(result);
        assertEquals("Dr. John Smith", result.getFullname());
        verify(doctorPersistence).update(dniA, updatedDoctor);
    }

    @Test
    void testUpdateDoctorNotFound() {
        // Given
        Doctor updatedDoctor = new Doctor(dniA, "Dr. John Smith", new BigDecimal("120000")); // Updated constructor

        when(doctorPersistence.update(dniA, updatedDoctor)).thenThrow(new NotFoundException("Doctor not found"));

        // When & Then
        NotFoundException exception = assertThrows(NotFoundException.class, () -> doctorService.updateDoctor(dniA, updatedDoctor));
        assertEquals("Doctor not found", exception.getMessage());
        verify(doctorPersistence).update(dniA, updatedDoctor);
    }
}
