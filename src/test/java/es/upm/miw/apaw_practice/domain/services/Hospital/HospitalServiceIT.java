package es.upm.miw.apaw_practice.domain.services.Hospital;

import es.upm.miw.apaw_practice.domain.exceptions.ConflictException;
import es.upm.miw.apaw_practice.domain.models.Hospital.Hospital;
import es.upm.miw.apaw_practice.domain.persistence_ports.Hospital.HospitalPersistence;

import org.junit.jupiter.api.Test;
import es.upm.miw.apaw_practice.TestConfig;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@TestConfig
public class HospitalServiceIT {

    @InjectMocks
    private HospitalService hospitalService;

    @Mock
    private HospitalPersistence hospitalPersistence;


    @Test
    void testCreateHospitalSuccessfully() {
        // Given
        Hospital hospital = new Hospital("Central Hospital", "Address 1", "123456789", 100, Collections.emptyList(), Collections.emptyList());

        when(hospitalPersistence.existsByName(hospital.getName())).thenReturn(false);
        when(hospitalPersistence.create(hospital)).thenReturn(hospital);

        // When
        Hospital createdHospital = hospitalService.create(hospital);

        // Then
        assertNotNull(createdHospital);
        assertEquals("Central Hospital", createdHospital.getName());
        verify(hospitalPersistence).existsByName(hospital.getName());
        verify(hospitalPersistence).create(hospital);
    }

    @Test
    void testCreateHospitalConflict() {
        // Given
        Hospital hospital = new Hospital("Central Hospital", "Address 1", "123456789", 100, Collections.emptyList(), Collections.emptyList());

        when(hospitalPersistence.existsByName(hospital.getName())).thenReturn(true); // Simulate conflict

        // When & Then
        Exception exception = assertThrows(ConflictException.class, () -> {
            hospitalService.create(hospital);
        });

        assertEquals("Hospital with name " + hospital.getName() + " already exists.", exception.getMessage());
        verify(hospitalPersistence).existsByName(hospital.getName());
        verify(hospitalPersistence, never()).create(any());
    }
}

