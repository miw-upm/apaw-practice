package es.upm.miw.apaw.functionaltests.university;

import es.upm.miw.apaw.adapters.resources.university.SubjectAssignmentResource;
import es.upm.miw.apaw.domain.models.university.Lesson;
import es.upm.miw.apaw.domain.models.university.SubjectAssignmentCapacityUpdating;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class SubjectAssignmentResourceFT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testGetLessons() {
        UUID subjectAssignmentId = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0300");

        webTestClient.get()
                .uri(SubjectAssignmentResource.SUBJECT_ASSIGNMENTS + SubjectAssignmentResource.ID + SubjectAssignmentResource.LESSONS, subjectAssignmentId)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Lesson.class)
                .value(lessons -> {
                    assertThat(lessons).isNotEmpty();
                    assertThat(lessons).hasSize(2);

                    Lesson lesson1 = lessons.getFirst();
                    assertThat(lesson1.getStartDate()).isEqualTo(LocalDateTime.of(2024, 1, 15, 9, 0));
                    assertThat(lesson1.getClassroom()).isEqualTo("A101");
                    assertThat(lesson1.getDuration()).isEqualTo(90);

                    Lesson lesson2 = lessons.get(1);
                    assertThat(lesson2.getStartDate()).isEqualTo(LocalDateTime.of(2024, 1, 17, 9, 0));
                    assertThat(lesson2.getClassroom()).isEqualTo("A101");
                    assertThat(lesson2.getDuration()).isEqualTo(90);
                });
    }

    @Test
    void testGetLessonsNotFound() {
        UUID nonExistentId = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff9999");

        webTestClient.get()
                .uri(SubjectAssignmentResource.SUBJECT_ASSIGNMENTS + SubjectAssignmentResource.ID + SubjectAssignmentResource.LESSONS, nonExistentId)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testGetLessonsWithMultipleLessons() {
        UUID subjectAssignmentId = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0301");

        webTestClient.get()
                .uri(SubjectAssignmentResource.SUBJECT_ASSIGNMENTS + SubjectAssignmentResource.ID + SubjectAssignmentResource.LESSONS, subjectAssignmentId)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Lesson.class)
                .value(lessons -> {
                    assertThat(lessons).isNotEmpty();
                    assertThat(lessons).hasSize(2);

                    Lesson lesson1 = lessons.getFirst();
                    assertThat(lesson1.getStartDate()).isEqualTo(LocalDateTime.of(2024, 1, 16, 11, 0));
                    assertThat(lesson1.getClassroom()).isEqualTo("B201");
                    assertThat(lesson1.getDuration()).isEqualTo(90);

                    Lesson lesson2 = lessons.get(1);
                    assertThat(lesson2.getStartDate()).isEqualTo(LocalDateTime.of(2024, 1, 18, 11, 0));
                    assertThat(lesson2.getClassroom()).isEqualTo("B201");
                    assertThat(lesson2.getDuration()).isEqualTo(90);
                });
    }

    @Test
    void testGetLessonsWithSingleLesson() {
        UUID subjectAssignmentId = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0302");

        webTestClient.get()
                .uri(SubjectAssignmentResource.SUBJECT_ASSIGNMENTS + SubjectAssignmentResource.ID + SubjectAssignmentResource.LESSONS, subjectAssignmentId)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Lesson.class)
                .value(lessons -> {
                    assertThat(lessons).isNotEmpty();
                    assertThat(lessons).hasSize(1);

                    Lesson lesson = lessons.getFirst();
                    assertThat(lesson.getStartDate()).isEqualTo(LocalDateTime.of(2024, 1, 15, 14, 0));
                    assertThat(lesson.getClassroom()).isEqualTo("C301");
                    assertThat(lesson.getDuration()).isEqualTo(60);
                });
    }

    @Test
    void testUpdateCapacities() {
        UUID subjectAssignmentId1 = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0308");
        UUID subjectAssignmentId2 = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0309");

        List<SubjectAssignmentCapacityUpdating> capacityUpdates = List.of(
                SubjectAssignmentCapacityUpdating.builder()
                        .id(subjectAssignmentId1)
                        .capacity(50)
                        .build(),
                SubjectAssignmentCapacityUpdating.builder()
                        .id(subjectAssignmentId2)
                        .capacity(75)
                        .build()
        );

        webTestClient.patch()
                .uri(SubjectAssignmentResource.SUBJECT_ASSIGNMENTS)
                .bodyValue(capacityUpdates)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testUpdateCapacitiesNotFound() {
        UUID nonExistentId = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff9999");

        List<SubjectAssignmentCapacityUpdating> capacityUpdates = List.of(
                SubjectAssignmentCapacityUpdating.builder()
                        .id(nonExistentId)
                        .capacity(50)
                        .build()
        );

        webTestClient.patch()
                .uri(SubjectAssignmentResource.SUBJECT_ASSIGNMENTS)
                .bodyValue(capacityUpdates)
                .exchange()
                .expectStatus().isNotFound();
    }
}
