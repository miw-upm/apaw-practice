package es.upm.miw.apaw.functionaltests.clinic;

import es.upm.miw.apaw.adapters.mongodb.clinic.daos.ClinicSeeder;
import es.upm.miw.apaw.adapters.resources.clinic.AppointmentResource;
import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.clinic.Appointment;
import es.upm.miw.apaw.domain.models.clinic.Diagnosis;
import es.upm.miw.apaw.domain.restclients.UserRestClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class AppointmentResourceFT {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private ClinicSeeder clinicSeeder;

    @MockitoBean
    private UserRestClient userRestClient;

    @BeforeEach
    void resetDb() {
        clinicSeeder.deleteAll();
        clinicSeeder.seedDatabase();
    }

    @Test
    void testPostCreateAppointment() {
        Appointment appointment = Appointment.builder()
                .appointmentDate(LocalDateTime.now().plusDays(1))
                .diagnoses(List.of(Diagnosis.builder().code("001").diagnosisDate(LocalDateTime.now()).severityLevel(1).build()))
                .reason("Check-up")
                .build();

        webTestClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path(AppointmentResource.APPOINTMENTS)
                        .queryParam("veterinarian-license", ClinicSeeder.LICENSE_DR_SMITH)
                        .queryParam("pet-microchip", ClinicSeeder.MICROCHIP_CHISPA)
                        .build())
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(appointment)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Appointment.class)
                .value(created -> assertThat(created.getReason()).isEqualTo("Check-up"));
    }

    @Test
    void testPatchAppointmentDate() {
        LocalDateTime newDate = LocalDateTime.now().plusDays(2);

        webTestClient.patch()
                .uri(uriBuilder -> uriBuilder
                        .path(AppointmentResource.APPOINTMENTS + AppointmentResource.ID)
                        .queryParam("newDate", newDate)
                        .build(ClinicSeeder.ID_APPOINTMENT_REVISION))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Appointment.class)
                .value(appointment -> assertThat(appointment.getAppointmentDate()).isEqualTo(newDate));
    }

    @Test
    void testGetMobilesByDiagnosisCode_ok() {
        String code = "FLU-001";

        BDDMockito.given(this.userRestClient.readById(any(UUID.class)))
                .willReturn(mockUser());

        this.webTestClient.get()
                .uri(AppointmentResource.APPOINTMENTS + "/diagnosis-code/{code}/mobiles", code)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(List.class)
                .value(mobiles -> {
                    assertThat(mobiles).isNotEmpty();
                    assertThat(mobiles).contains("600123456");
                });
    }

    @Test
    void testGetMobilesByDiagnosisCode_notFound() {
        this.webTestClient.get()
                .uri("/diagnoses/{code}/mobiles", "INVALID CODE")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound();
    }

    private static UserDto mockUser() {
        return UserDto.builder()
                .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0001"))
                .mobile("600123456")
                .firstName("Miguel")
                .build();
    }

}