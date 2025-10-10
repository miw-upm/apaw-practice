package es.upm.miw.apaw.functionaltests.studentcouncil;

import es.upm.miw.apaw.adapters.resources.studentcouncil.StudentIssueResource;
import es.upm.miw.apaw.domain.models.studentcouncil.StudentIssue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class StudentIssueResourceFT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreateStudentIssue() {
        StudentIssue issue = StudentIssue.builder()
                .statement("Issue for testing")
                .urgency(5)
                .build();

        webTestClient.post()
                .uri(StudentIssueResource.STUDENT_ISSUES)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(issue)
                .exchange()
                .expectStatus().isOk()
                .expectBody(StudentIssue.class)
                .value(created -> {
                    assertThat(created.getId()).isNotNull();
                    assertThat(created.getStatement()).isEqualTo("Issue for testing");
                    assertThat(created.getClosed()).isFalse();
                    assertThat(created.getReplies()).isEmpty();
                });
    }
}
