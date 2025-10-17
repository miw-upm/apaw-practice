package es.upm.miw.apaw.functionaltests.recruiting;

import es.upm.miw.apaw.adapters.mongodb.recruiting.daos.RecruitingSeeder;
import es.upm.miw.apaw.adapters.resources.recruiting.AttendeeResource;
import es.upm.miw.apaw.domain.models.recruiting.Attendee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class AttendeeResourceFT {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private RecruitingSeeder recruitingSeeder;

    @BeforeEach
    void resetDb() {
        recruitingSeeder.deleteAll();
        recruitingSeeder.seedDatabase();
    }

    // --- READ endpoint test -----------------------------------------------------------

    @Test
    void testReadAttendeeByEmail() {
        String email = "felix.issle@test.com";

        webTestClient.get()
                .uri(AttendeeResource.ATTENDEES + "/{email}", email)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(Attendee.class)
                .value(attendee -> {
                    assertThat(attendee.getEmailAddress()).isEqualTo(email);
                    assertThat(attendee.getFullName()).isEqualTo("Felix Issle");
                    assertThat(attendee.getPhoneNumber()).isEqualTo("+4173912799");
                });
    }

    @Test
    void testReadAttendeeByEmailNotFound() {
        webTestClient.get()
                .uri(AttendeeResource.ATTENDEES + "/{email}", "nonexistent@example.com")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound();
    }

    // --- DELETE endpoint tests --------------------------------------------------------

    @Test
    void testDeleteAttendeeByEmail() {
        String email = "karolyn.sanz@test.com";

        webTestClient.delete()
                .uri(AttendeeResource.ATTENDEES + "/{email}", email)
                .exchange()
                .expectStatus().isNoContent();

        webTestClient.get()
                .uri(AttendeeResource.ATTENDEES + "/{email}", email)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testDeleteAttendeeByEmailNotFound() {
        webTestClient.delete()
                .uri(AttendeeResource.ATTENDEES + "/{email}", "unknown@example.com")
                .exchange()
                .expectStatus().isNotFound();
    }
}