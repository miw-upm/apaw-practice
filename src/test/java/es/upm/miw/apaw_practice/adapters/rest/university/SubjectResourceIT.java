package es.upm.miw.apaw_practice.adapters.rest.university;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.university.Classroom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static es.upm.miw.apaw_practice.adapters.rest.university.SubjectResource.SUBJECTS;
import static es.upm.miw.apaw_practice.adapters.rest.university.SubjectResource.REFERENCE_ID;
import static es.upm.miw.apaw_practice.adapters.rest.university.SubjectResource.CLASSROOM;

@RestTestConfig
public class SubjectResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testUpdateClassroom() {
        Classroom classroom = new Classroom("ETSISI", 3004, 10);
        this.webTestClient
                .put()
                .uri(SUBJECTS + REFERENCE_ID + CLASSROOM, 613000096)
                .body(BodyInserters.fromValue(classroom))
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testUpdateClassroomNotFound() {
        Classroom classroom = new Classroom("ETSISI", 3004, 10);
        this.webTestClient
                .put()
                .uri(SUBJECTS + REFERENCE_ID + CLASSROOM, 111)
                .body(BodyInserters.fromValue(classroom))
                .exchange()
                .expectStatus().isNotFound();
    }
}
