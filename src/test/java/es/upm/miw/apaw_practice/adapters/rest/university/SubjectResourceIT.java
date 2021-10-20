package es.upm.miw.apaw_practice.adapters.rest.university;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.university.Classroom;
import es.upm.miw.apaw_practice.domain.models.university.SubjectCreditsUpdating;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.util.Arrays;
import java.util.List;

import static es.upm.miw.apaw_practice.adapters.rest.university.SubjectResource.SUBJECTS;
import static es.upm.miw.apaw_practice.adapters.rest.university.SubjectResource.REFERENCE_ID;
import static es.upm.miw.apaw_practice.adapters.rest.university.SubjectResource.CLASSROOM;

@RestTestConfig
public class SubjectResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testUpdateClassroom() {
        Classroom classroom = Classroom.builder().school("ETSISI").number(3004).capacity(10).build();
        this.webTestClient
                .put()
                .uri(SUBJECTS + REFERENCE_ID + CLASSROOM, 613000096)
                .body(BodyInserters.fromValue(classroom))
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testUpdateClassroomNotFound() {
        Classroom classroom = Classroom.builder().school("ETSISI").number(3004).capacity(10).build();
        this.webTestClient
                .put()
                .uri(SUBJECTS + REFERENCE_ID + CLASSROOM, 111)
                .body(BodyInserters.fromValue(classroom))
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testUpdateCredits() {
        List<SubjectCreditsUpdating> subjectCreditsUpdatings = Arrays.asList(
                new SubjectCreditsUpdating(615000225, 9),
                new SubjectCreditsUpdating(615000232, 6)
        );

        this.webTestClient
                .patch()
                .uri(SUBJECTS)
                .body(BodyInserters.fromValue(subjectCreditsUpdatings))
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testUpdateCreditsNotFound() {
        List<SubjectCreditsUpdating> subjectCreditsUpdatings = List.of(
                new SubjectCreditsUpdating(111, 9)
        );

        this.webTestClient
                .patch()
                .uri(SUBJECTS)
                .body(BodyInserters.fromValue(subjectCreditsUpdatings))
                .exchange()
                .expectStatus().isNotFound();
    }
}
