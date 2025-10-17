package es.upm.miw.apaw.domain.services.clinic;

import es.upm.miw.apaw.domain.models.clinic.Doctor;
import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.persistenceports.clinic.DoctorPersistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Optional;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// Usa Mockito para simular la persistencia y probar solo la lógica del servicio
@ExtendWith(MockitoExtension.class)
class DoctorServiceTest {

    // Simulación del puerto de persistencia (la base de datos)
    @Mock
    private DoctorPersistence doctorPersistence;

    // Inyecta el mock de persistencia en el servicio a probar
    @InjectMocks
    private DoctorService doctorService;

    private Doctor testDoctor;
    private static final Long TEST_LICENSE = 999000L;

    @BeforeEach
    void setUp() {
        testDoctor = Doctor.builder()
                .licenseNumber(TEST_LICENSE)
                .name("Dr. Test")
                .specialty("Testology")
                .build();
    }

    // --- Tests para CREATE ---
    @Test
    void testCreate() {
        when(doctorPersistence.readByLicenseNumber(TEST_LICENSE)).thenReturn(Optional.empty());
        when(doctorPersistence.create(testDoctor)).thenReturn(testDoctor);

        Doctor createdDoctor = doctorService.create(testDoctor);

        assertNotNull(createdDoctor);
        verify(doctorPersistence).readByLicenseNumber(TEST_LICENSE);
        verify(doctorPersistence).create(testDoctor);
    }

    @Test
    void testCreateConflict() {
        when(doctorPersistence.readByLicenseNumber(TEST_LICENSE)).thenReturn(Optional.of(testDoctor));
        assertThrows(ConflictException.class, () -> doctorService.create(testDoctor));
        verify(doctorPersistence).readByLicenseNumber(TEST_LICENSE);
        verify(doctorPersistence, never()).create(testDoctor);
    }

    // --- Tests para READ ALL ---
    @Test
    void testReadAll() {
        doctorService.readAll();
        verify(doctorPersistence).readAll();
    }

    // --- Tests para READ BY LICENSE (GET /{licenseNumber}) ---
    @Test
    void testReadByLicenseNumberSuccessfully() {
        when(doctorPersistence.readByLicenseNumber(TEST_LICENSE)).thenReturn(Optional.of(testDoctor));
        Doctor foundDoctor = doctorService.readByLicenseNumber(TEST_LICENSE);
        assertEquals(TEST_LICENSE, foundDoctor.getLicenseNumber());
    }

    @Test
    void testReadByLicenseNumberNotFound() {
        when(doctorPersistence.readByLicenseNumber(TEST_LICENSE)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> doctorService.readByLicenseNumber(TEST_LICENSE));
    }

    // --- Tests para UPDATE (PUT /{licenseNumber}) ---
    @Test
    void testUpdateSuccessfully() {
        // Simula que existe en la persistencia para la validación de existencia
        when(doctorPersistence.readByLicenseNumber(TEST_LICENSE)).thenReturn(Optional.of(testDoctor));
        // Simula la llamada a la persistencia para actualizar
        when(doctorPersistence.update(testDoctor)).thenReturn(testDoctor);

        Doctor updated = doctorService.update(TEST_LICENSE, testDoctor);

        assertNotNull(updated);
        verify(doctorPersistence).readByLicenseNumber(TEST_LICENSE);
        verify(doctorPersistence).update(testDoctor);
    }

    @Test
    void testUpdateNotFound() {
        // Simula que NO existe en la persistencia (para la validación de existencia)
        when(doctorPersistence.readByLicenseNumber(TEST_LICENSE)).thenReturn(Optional.empty());

        // El servicio debe lanzar NotFoundException
        assertThrows(NotFoundException.class, () -> doctorService.update(TEST_LICENSE, testDoctor));

        verify(doctorPersistence).readByLicenseNumber(TEST_LICENSE);
        verify(doctorPersistence, never()).update(any());
    }

    @Test
    void testUpdateConflictLicenseMismatch() {
        Long conflictingLicense = 1000L;
        // La validación de conflicto ocurre antes de llamar a la persistencia
        assertThrows(ConflictException.class, () -> doctorService.update(conflictingLicense, testDoctor));

        verify(doctorPersistence, never()).readByLicenseNumber(any());
        verify(doctorPersistence, never()).update(any());
    }

    // --- Tests para DELETE (DELETE /{licenseNumber}) ---

    @Test
    void testDeleteSuccessfully() {
        // 1. Simula que el doctor EXISTE para la validación del servicio
        when(doctorPersistence.readByLicenseNumber(TEST_LICENSE)).thenReturn(Optional.of(testDoctor));

        // 2. Ejecuta el borrado
        doctorService.delete(TEST_LICENSE);

        // 3. Verifica que se llamó a la validación y al borrado en la persistencia
        verify(doctorPersistence).readByLicenseNumber(TEST_LICENSE);
        verify(doctorPersistence).delete(TEST_LICENSE);
    }

    @Test
    void testDeleteNotFound() {
        // 1. Simula que el doctor NO EXISTE
        when(doctorPersistence.readByLicenseNumber(TEST_LICENSE)).thenReturn(Optional.empty());

        // 2. Verifica que se lance NotFoundException
        assertThrows(NotFoundException.class, () -> doctorService.delete(TEST_LICENSE));

        // 3. Verifica que NO se llamó al método de borrado
        verify(doctorPersistence).readByLicenseNumber(TEST_LICENSE);
        verify(doctorPersistence, never()).delete(TEST_LICENSE);
    }

    // --- Tests para PATCH (PATCH /{licenseNumber}) ---

    @Test
    void testPatchSuccessfully() {
        // 1. Configurar datos de actualización
        Map<String, Object> updates = new HashMap<>();
        updates.put("specialty", "Dermatology");
        updates.put("name", "Dr. Patch Test");

        // 2. Simular lectura inicial
        when(doctorPersistence.readByLicenseNumber(TEST_LICENSE)).thenReturn(Optional.of(testDoctor));

        // 3. Simular la respuesta de la actualización (creamos la nueva instancia de forma manual)
        // Corrección: Usamos Doctor.builder() en lugar de testDoctor.toBuilder()
        Doctor patchedDoctor = Doctor.builder()
                .licenseNumber(TEST_LICENSE) // Conserva el ID
                .name("Dr. Patch Test")      // Nuevo nombre
                .specialty("Dermatology")    // Nueva especialidad
                .build();

        when(doctorPersistence.update(any(Doctor.class))).thenReturn(patchedDoctor);

        // 4. Ejecutar
        Doctor result = doctorService.patch(TEST_LICENSE, updates);

        // 5. Verificar
        assertEquals("Dermatology", result.getSpecialty());
        assertEquals("Dr. Patch Test", result.getName());
        verify(doctorPersistence).readByLicenseNumber(TEST_LICENSE);
        // Verifica que se llamó a 'update' con un objeto que tiene los campos parchados
        verify(doctorPersistence).update(argThat(d -> d.getSpecialty().equals("Dermatology") && d.getName().equals("Dr. Patch Test")));
    }

    @Test
    void testPatchNotFound() {
        // Simula que NO existe
        when(doctorPersistence.readByLicenseNumber(TEST_LICENSE)).thenReturn(Optional.empty());

        // Verifica que lance NotFoundException
        assertThrows(NotFoundException.class, () -> doctorService.patch(TEST_LICENSE, new HashMap<>()));

        verify(doctorPersistence).readByLicenseNumber(TEST_LICENSE);
        verify(doctorPersistence, never()).update(any());
    }
}
