package es.upm.miw.apaw.functionaltests.university;

import es.upm.miw.apaw.adapters.resources.university.SubjectResource;
import es.upm.miw.apaw.domain.models.university.Subject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
public class SubjectResourceFT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreate() {
        Subject subject = Subject.builder()
                .name("Test Subject")
                .description("Test Description")
                .credits(6)
                .build();

        webTestClient.post()
                .uri(SubjectResource.SUBJECTS)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(subject)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Subject.class)
                .value(created -> assertThat(created).isNotNull());
    }

    @Test
    void testCreateNameConflict() {
        Subject subject = Subject.builder()
                .name("SN001") // This name already exists in the seeder
                .description("Test Description")
                .credits(6)
                .build();

        webTestClient.post()
                .uri(SubjectResource.SUBJECTS)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(subject)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void testCreateBadRequest() {
        Subject subject = Subject.builder()
                .name("") // Empty name should fail validation
                .description("Test Description")
                .credits(6)
                .build();

        webTestClient.post()
                .uri(SubjectResource.SUBJECTS)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(subject)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testCreateBadRequestMissingCredits() {
        Subject subject = Subject.builder()
                .name("Test Subject")
                .description("Test Description")
                // Missing credits - should fail validation
                .build();

        webTestClient.post()
                .uri(SubjectResource.SUBJECTS)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(subject)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testCreateBadRequestNullName() {
        Subject subject = Subject.builder()
                .name(null) // Null name should fail validation
                .description("Test Description")
                .credits(6)
                .build();

        webTestClient.post()
                .uri(SubjectResource.SUBJECTS)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(subject)
                .exchange()
                .expectStatus().isBadRequest();
    }
}
