package es.upm.miw.apaw_practice.adapters.rest.university;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.university.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.util.Arrays;
import java.util.List;

import static es.upm.miw.apaw_practice.adapters.rest.university.StudentResource.SEARCH;
import static es.upm.miw.apaw_practice.adapters.rest.university.StudentResource.STUDENTS;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
                .uri(STUDENTS)
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
                .uri(STUDENTS)
                .body(BodyInserters.fromValue(student))
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void testFindDniListByClassroomSchool() {
        List<String> dniCorrectList = Arrays.asList("12345678X", "112233445E");
        this.webTestClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder.path(STUDENTS + SEARCH)
                                .queryParam("q", "classroomSchool:ETSIINF")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(List.class)
                .value(System.out::println)
                .consumeWith(dniList -> {
                    assertNotNull(dniList.getResponseBody());
                    assertTrue(dniList.getResponseBody().containsAll(dniCorrectList));
                });
    }
}
