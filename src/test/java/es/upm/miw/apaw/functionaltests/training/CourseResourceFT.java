package es.upm.miw.apaw.functionaltests.training;

import es.upm.miw.apaw.adapters.in.training.CourseResource;
import es.upm.miw.apaw.domain.model.training.Course;
import es.upm.miw.apaw.domain.model.training.CourseDurationUpdate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.client.RestTestClient;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.TrainingSeederForDev.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class CourseResourceFT {

    @LocalServerPort
    private int port;
    private RestTestClient restTestClient;

    @BeforeEach
    void setUp() {
        this.restTestClient = RestTestClient.bindToServer()
                .baseUrl("http://localhost:" + this.port).build();
    }

    @Test
    void testRead() {
        this.restTestClient.get().uri(CourseResource.COURSES + "/" + COURSE_ID_0)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Course.class)
                .value(body -> assertThat(body).usingRecursiveComparison().isEqualTo(COURSE_0));
    }

    @Test
    void testReadNotFound() {
        UUID id = UUID.randomUUID();
        this.restTestClient.get().uri(CourseResource.COURSES + "/" + id)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(Map.class)
                .value(body -> assertThat((String) body.get("message")).contains(id.toString()));
    }

    @Test
    void testFindAll() {
        this.restTestClient.get().uri(CourseResource.COURSES)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Course[].class)
                .value(courses -> {
                    assertThat(courses).extracting(Course::getId)
                        .containsSubsequence(COURSE_ID_3, COURSE_ID_1, COURSE_ID_2, COURSE_ID_0);
                });
    }

    @Test
    void testCreate() {
        Course course = new Course(null, "React " + UUID.randomUUID(), "Ref-5", 35, true, LocalDate.of(2025, 5, 5));
        this.restTestClient.post().uri(CourseResource.COURSES)
                .body(course)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Course.class)
                .value(body -> {
                    assertNotNull(body.getId());
                    assertNotNull(body.getName());
                });
    }

    @Test
    void testCreateDuplicateName() {
        Course course = new Course(null, COURSE_0.getName(), "Ref-Dup", 20, false, LocalDate.now());
        this.restTestClient.post().uri(CourseResource.COURSES)
                .body(course)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void testUpdate() {
        Course course = new Course(null, "Angular Advanced", "Ref-2-Adv", 35, true, LocalDate.of(2025, 2, 20));
        this.restTestClient.put().uri(CourseResource.COURSES + "/" + COURSE_ID_1)
                .body(course)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Course.class)
                .value(body -> {
                    assertEquals("Angular Advanced", body.getName());
                    assertEquals(35, body.getDurationHours());
                });
    }

    @Test
    void testUpdateNotFound() {
        Course course = new Course(null, "Not Found Course", "Ref-X", 10, true, LocalDate.now());
        this.restTestClient.put().uri(CourseResource.COURSES + "/" + UUID.randomUUID())
                .body(course)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testUpdateDuplicateName() {
        Course course = new Course(null, COURSE_2.getName(), "Ref-X", 10, true, LocalDate.now());
        this.restTestClient.put().uri(CourseResource.COURSES + "/" + COURSE_ID_1)
                .body(course)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void testPatch() {
        List<CourseDurationUpdate> updates = List.of(
                new CourseDurationUpdate(COURSE_ID_2, 18),
                new CourseDurationUpdate(COURSE_ID_3, 55)
        );
        this.restTestClient.patch().uri(CourseResource.COURSES)
                .body(updates)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testPatchNotFoundChangesNothing() {
        UUID missingId = UUID.randomUUID();
        this.restTestClient.patch().uri(CourseResource.COURSES)
                .body(List.of(new CourseDurationUpdate(COURSE_ID_2, 25),
                        new CourseDurationUpdate(missingId, 60)))
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testPatchRepeatedIdChangesNothing() {
        this.restTestClient.patch().uri(CourseResource.COURSES)
                .body(List.of(new CourseDurationUpdate(COURSE_ID_2, 25),
                        new CourseDurationUpdate(COURSE_ID_2, 60)))
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testPatchEmptyList() {
        this.restTestClient.patch().uri(CourseResource.COURSES)
                .body(List.of())
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testPatchMissingId() {
        this.restTestClient.patch().uri(CourseResource.COURSES)
                .body(List.of(new CourseDurationUpdate(null, 25)))
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testPatchMissingDuration() {
        this.restTestClient.patch().uri(CourseResource.COURSES)
                .body(List.of(new CourseDurationUpdate(COURSE_ID_0, null)))
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testDelete() {
        this.restTestClient.delete().uri(CourseResource.COURSES + "/" + COURSE_ID_2)
                .exchange()
                .expectStatus().isNoContent();
    }
}



