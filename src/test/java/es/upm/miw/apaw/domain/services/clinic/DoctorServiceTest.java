package es.upm.miw.apaw.domain.services.clinic;

import es.upm.miw.apaw.domain.models.clinic.Doctor;
import es.upm.miw.apaw.domain.persistenceports.clinic.DoctorPersistence;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DoctorServiceTest {

    @InjectMocks
    private DoctorService doctorService;

    @Mock
    private DoctorPersistence doctorPersistence;

    @Test
    void testReadAll() {
        // 1. Datos simulados (Mocks)
        Doctor doctor1 = Doctor.builder()
                .id(UUID.randomUUID().toString()).licenseNumber(100L).name("Alice").specialty("Dentistry").build();
        Doctor doctor2 = Doctor.builder()
                .id(UUID.randomUUID().toString()).licenseNumber(200L).name("Bob").specialty("Cardiology").build();

        // 2. Definir comportamiento: cuando se llama a readAll(), devuelve el Stream simulado
        when(doctorPersistence.readAll()).thenReturn(Stream.of(doctor1, doctor2));

        // 3. Ejecutar y verificar
        List<Doctor> doctors = doctorService.readAll();

        assertEquals(2, doctors.size());
        assertEquals("Alice", doctors.get(0).getName());
    }
}