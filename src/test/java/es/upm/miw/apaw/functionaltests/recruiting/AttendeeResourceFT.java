package es.upm.miw.apaw.functionaltests.recruiting;

import es.upm.miw.apaw.adapters.mongodb.recruiting.daos.AttendeeRepository;
import es.upm.miw.apaw.adapters.mongodb.recruiting.entities.AttendeeEntity;
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

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class AttendeeResourceFT {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private AttendeeRepository attendeeRepository;

    private AttendeeEntity savedEntity;

    @BeforeEach
    void setUp() {
        attendeeRepository.deleteAll();

        savedEntity = attendeeRepository.save(
                AttendeeEntity.builder()
                        .id(UUID.randomUUID())
                        .emailAddress("john.doe@example.com")
                        .fullName("John Doe")
                        .phoneNumber("600123456")
                        .user(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000"))
                        .build()
        );
    }

    @Test
    void testReadAttendeeByEmail() {
        webTestClient.get()
                .uri(AttendeeResource.ATTENDEES + "/{email}", savedEntity.getEmailAddress())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(Attendee.class)
                .value(attendee -> {
                    assertThat(attendee.getEmailAddress()).isEqualTo(savedEntity.getEmailAddress());
                    assertThat(attendee.getFullName()).isEqualTo("John Doe");
                    assertThat(attendee.getPhoneNumber()).isEqualTo("600123456");
                    assertThat(attendee.getUser()).isNotNull();
                    assertThat(attendee.getUser().getId()).isEqualTo(savedEntity.getUser());
                });
    }

    @Test
    void testReadAttendeeByEmailNotFound() {
        webTestClient.get()
                .uri(AttendeeResource.ATTENDEES + "/{email}", "not.exists@example.com")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound();
    }
}