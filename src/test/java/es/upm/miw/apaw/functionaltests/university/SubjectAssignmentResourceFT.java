package es.upm.miw.apaw.functionaltests.university;

import es.upm.miw.apaw.adapters.resources.university.SubjectAssignmentResource;
import es.upm.miw.apaw.domain.models.university.Lesson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDateTime;
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
}
