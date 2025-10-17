package es.upm.miw.apaw.functionaltests.clinic;

import es.upm.miw.apaw.adapters.mongodb.clinic.daos.DoctorRepository;
import es.upm.miw.apaw.adapters.resources.clinic.DoctorResource;
import es.upm.miw.apaw.domain.models.clinic.Doctor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class DoctorResourceFT {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private DoctorRepository doctorRepository;

    @BeforeEach
    void cleanUpDb() {
        this.doctorRepository.deleteAll();
    }

    // Helper para crear doctores (Usado por todos los tests)
    private Doctor createDoctor(Long licenseNumber, String specialty) {
        Doctor doctor = Doctor.builder()
                .licenseNumber(licenseNumber)
                .name("Dr. Test " + licenseNumber)
                .specialty(specialty)
                .build();

        // Ejecuta el POST para crearlo en la DB
        return this.webTestClient
                .post()
                .uri(DoctorResource.DOCTORS)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(doctor)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Doctor.class)
                .returnResult().getResponseBody();
    }

    // --- Pruebas POST /doctors --------------------------------------

    @Test
    void testCreateDoctorSuccessfully() {
        createDoctor(123456L, "Cirugía");
    }

    @Test
    void testCreateDoctorConflict() {
        createDoctor(789012L, "Pediatría");
        Doctor doctorDuplicate = Doctor.builder().licenseNumber(789012L).name("Duplicado").specialty("General").build();

        this.webTestClient
                .post()
                .uri(DoctorResource.DOCTORS)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(doctorDuplicate)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }

    // --- Pruebas PUT /doctors/{licenseNumber} ------------------------

    @Test
    void testUpdateDoctorSuccessfully() {
        // 1. Crear un doctor base (ej: 111111, Neumología)
        Long license = 111111L;
        createDoctor(license, "Neumología");

        // 2. Definir los datos de actualización (ej: Cardiología)
        Doctor updatedInfo = Doctor.builder()
                .licenseNumber(license) // Mismo número de licencia
                .name("Dr. Juan Actualizado")
                .specialty("Cardiología") // Nuevo valor
                .build();

        // 3. Ejecutar el PUT.
        this.webTestClient
                .put()
                .uri(DoctorResource.DOCTORS + DoctorResource.LICENSE_NUMBER, license)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(updatedInfo)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Doctor.class)
                .value(responseDoctor -> {
                    // 4. Verificar que se actualizó el campo
                    assertThat(responseDoctor.getSpecialty()).isEqualTo("Cardiología");
                    assertThat(responseDoctor.getName()).isEqualTo("Dr. Juan Actualizado");
                    assertThat(responseDoctor.getLicenseNumber()).isEqualTo(license);
                });
    }

    @Test
    void testUpdateDoctorNotFound() {
        Long nonExistentLicense = 999999L;

        // Doctor con datos para el body
        Doctor dummyDoctor = Doctor.builder()
                .licenseNumber(nonExistentLicense)
                .name("Dummy")
                .specialty("Dummy")
                .build();

        this.webTestClient
                .put()
                .uri(DoctorResource.DOCTORS + DoctorResource.LICENSE_NUMBER, nonExistentLicense)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(dummyDoctor)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.NOT_FOUND); // Espera 404
    }

    @Test
    void testUpdateDoctorConflictBadRequest() {
        Long correctLicenseInPath = 222222L;
        Long conflictingLicenseInBody = 333333L;

        // 1. Crear el doctor que existe (222222)
        createDoctor(correctLicenseInPath, "Dermatología");

        // 2. Intentar actualizar 222222, pero el body dice 333333
        Doctor conflictingBody = Doctor.builder()
                .licenseNumber(conflictingLicenseInBody)
                .name("Mala Petición")
                .specialty("Dermatología")
                .build();

        this.webTestClient
                .put()
                .uri(DoctorResource.DOCTORS + DoctorResource.LICENSE_NUMBER, correctLicenseInPath)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(conflictingBody)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT); // Espera 409
    }

    // --- Pruebas DELETE /doctors/{licenseNumber} ------------------------

    @Test
    void testDeleteDoctorSuccessfully() {
        Long licenseToDelete = 888888L;
        // 1. Crear el doctor que se va a eliminar
        createDoctor(licenseToDelete, "Odontología");

        // 2. Ejecutar el DELETE
        this.webTestClient
                .delete()
                .uri(DoctorResource.DOCTORS + DoctorResource.LICENSE_NUMBER, licenseToDelete)
                .exchange()
                .expectStatus().isOk(); // Espera 200 OK

        // 3. Verificar que ya no existe (Intento de GET o PUT devuelve 404)
        this.webTestClient
                .get()
                .uri(DoctorResource.DOCTORS + DoctorResource.LICENSE_NUMBER, licenseToDelete)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void testDeleteDoctorNotFound() {
        Long nonExistentLicense = 777777L;
        // 1. Ejecutar el DELETE sobre una licencia que no existe
        this.webTestClient
                .delete()
                .uri(DoctorResource.DOCTORS + DoctorResource.LICENSE_NUMBER, nonExistentLicense)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.NOT_FOUND); // Espera 404
    }

    // --- Pruebas PATCH /doctors/{licenseNumber} ----------------------

    @Test
    void testPatchDoctorSpecialtySuccessfully() {
        Long licenseNumber = 555555L;
        // 1. Crear doctor base
        createDoctor(licenseNumber, "Cardiology");

        // 2. Crear patch solo con el campo specialty
        Map<String, Object> patch = new HashMap<>();
        patch.put("specialty", "Dermatology");

        // 3. Ejecutar PATCH
        this.webTestClient
                .patch()
                .uri(DoctorResource.DOCTORS + DoctorResource.LICENSE_NUMBER, licenseNumber)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(patch)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Doctor.class)
                .value(responseDoctor -> {
                    // 4. Verificar que solo se actualizó la especialidad
                    assertThat(responseDoctor.getSpecialty()).isEqualTo("Dermatology");
                    // 5. Verificar que el nombre sigue igual
                    assertThat(responseDoctor.getName()).isEqualTo("Dr. Test 555555");
                    assertThat(responseDoctor.getLicenseNumber()).isEqualTo(licenseNumber);
                });
    }

    @Test
    void testPatchDoctorNotFound() {
        Long nonExistentLicense = 666666L;
        // 1. Crear patch
        Map<String, Object> patch = new HashMap<>();
        patch.put("specialty", "Ortopedia");

        // 2. Ejecutar PATCH sobre una licencia que no existe
        this.webTestClient
                .patch()
                .uri(DoctorResource.DOCTORS + DoctorResource.LICENSE_NUMBER, nonExistentLicense)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(patch)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.NOT_FOUND); // Espera 404
    }


    // --- Pruebas GET /doctors ---------------------------------------

    @Test
    void testReadAllDoctors() {
        createDoctor(111222L, "Gastro");
        createDoctor(333444L, "Oftalmo");

        this.webTestClient
                .get()
                .uri(DoctorResource.DOCTORS)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Doctor.class)
                .value(doctors -> {
                    assertThat(doctors).hasSize(2);
                });
    }
}