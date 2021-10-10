package es.upm.miw.apaw_practice.adapters.rest.university;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.university.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@RestTestConfig
public class StudentResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreate() {
        Student student =
                new Student("12121212D", "Teresa de Ahumada", false);
        this.webTestClient
                .post()
                .uri(StudentResource.STUDENTS)
                .body(BodyInserters.fromValue(student))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Student.class)
                .value(Assertions::assertNotNull);
    }

    @Test
    void testCreateConflict() {
        Student student =
                new Student("12345678X", "Ada Lovelace", true);
        this.webTestClient
                .post()
                .uri(StudentResource.STUDENTS)
                .body(BodyInserters.fromValue(student))
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }
}
