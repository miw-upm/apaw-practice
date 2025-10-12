package es.upm.miw.apaw.functionaltests.clinic;

import es.upm.miw.apaw.domain.models.clinic.Doctor;
import es.upm.miw.apaw.adapters.resources.clinic.DoctorResource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class DoctorResourceFT {

    @Autowired
    private WebTestClient webTestClient;

    // Método para llamar al endpoint GET /clinic/doctors y obtener la lista de Doctores
    public List<Doctor> readAll() {
        return this.webTestClient
                .get().uri(DoctorResource.DOCTORS)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Doctor.class)
                .returnResult().getResponseBody();
    }

    // Test para validar la funcionalidad del listado (#1216)
    @Test
    void testReadAll() {
        List<Doctor> doctors = this.readAll();

        // 1. Verificación básica: La lista no debe ser nula
        assertNotNull(doctors, "La respuesta del endpoint no debe ser nula.");

        // 2. Verificación de datos: Debe haber al menos un doctor (asumiendo datos del ClinicSeeder)
        assertTrue(doctors.size() > 0, "Debe devolver al menos un doctor insertado por el Seeder.");

        // 3. Verificación de contenido: El primer doctor debe tener un número de licencia
        assertThat(doctors.get(0).getLicenseNumber())
                .isNotNull()
                .isPositive();

        // 4. Verificación de tipo de respuesta (opcional, pero buena práctica)
        assertThat(doctors).isInstanceOf(List.class);
    }
}