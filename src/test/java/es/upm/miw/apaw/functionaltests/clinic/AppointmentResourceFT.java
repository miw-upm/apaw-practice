package es.upm.miw.apaw.functionaltests.clinic;

import es.upm.miw.apaw.adapters.mongodb.clinic.daos.ClinicSeeder;
import es.upm.miw.apaw.adapters.resources.clinic.AppointmentResource;
import es.upm.miw.apaw.domain.models.clinic.Appointment;
import es.upm.miw.apaw.domain.models.clinic.Diagnosis;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class AppointmentResourceFT {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private ClinicSeeder clinicSeeder;

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

}