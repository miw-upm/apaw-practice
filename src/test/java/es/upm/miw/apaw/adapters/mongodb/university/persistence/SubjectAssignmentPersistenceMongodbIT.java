package es.upm.miw.apaw.adapters.mongodb.university.persistence;

import es.upm.miw.apaw.adapters.mongodb.university.daos.SubjectAssignmentRepository;
import es.upm.miw.apaw.adapters.mongodb.university.entities.SubjectAssignmentEntity;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.university.Lesson;
import es.upm.miw.apaw.domain.models.university.Subject;
import es.upm.miw.apaw.domain.models.university.SubjectAssignment;
import es.upm.miw.apaw.domain.models.university.Teacher;
import es.upm.miw.apaw.domain.persistenceports.university.SubjectAssignmentPersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
class SubjectAssignmentPersistenceMongodbIT {

    @Autowired
    private SubjectAssignmentPersistence subjectAssignmentPersistence;

    @Autowired
    private SubjectAssignmentRepository subjectAssignmentRepository;

    @Test
    void testGetById() {
        UUID subjectAssignmentId = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0301");
        
        SubjectAssignment result = subjectAssignmentPersistence.getById(subjectAssignmentId);
        
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(subjectAssignmentId);
        assertThat(result.getLessons()).isNotEmpty();
        assertThat(result.getLessons()).hasSize(2);

        Lesson lesson1 = result.getLessons().get(0);
        assertThat(lesson1.getStartDate()).isEqualTo(LocalDateTime.of(2024, 1, 16, 11, 0));
        assertThat(lesson1.getClassroom()).isEqualTo("B201");
        assertThat(lesson1.getDuration()).isEqualTo(90);
        
        Lesson lesson2 = result.getLessons().get(1);
        assertThat(lesson2.getStartDate()).isEqualTo(LocalDateTime.of(2024, 1, 18, 11, 0));
        assertThat(lesson2.getClassroom()).isEqualTo("B201");
        assertThat(lesson2.getDuration()).isEqualTo(90);
    }

    @Test
    void testGetByIdNotFound() {
        UUID nonExistentId = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff9999");
        
        assertThatThrownBy(() -> subjectAssignmentPersistence.getById(nonExistentId))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("SubjectAssignment id: " + nonExistentId);
    }

    @Test
    void testGetByIdWithMultipleLessons() {
        UUID subjectAssignmentId = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0302");
        
        SubjectAssignment result = subjectAssignmentPersistence.getById(subjectAssignmentId);
        
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(subjectAssignmentId);
        assertThat(result.getLessons()).isNotEmpty();
        assertThat(result.getLessons()).hasSize(1);

        Lesson lesson = result.getLessons().get(0);
        assertThat(lesson.getStartDate()).isEqualTo(LocalDateTime.of(2024, 1, 15, 14, 0));
        assertThat(lesson.getClassroom()).isEqualTo("C301");
        assertThat(lesson.getDuration()).isEqualTo(60);
    }

    @Test
    void testGetByIdWithSingleLesson() {
        UUID subjectAssignmentId = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0303");
        
        SubjectAssignment result = subjectAssignmentPersistence.getById(subjectAssignmentId);
        
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(subjectAssignmentId);
        assertThat(result.getLessons()).isNotEmpty();
        assertThat(result.getLessons()).hasSize(2);

        Lesson lesson1 = result.getLessons().get(0);
        assertThat(lesson1.getStartDate()).isEqualTo(LocalDateTime.of(2024, 1, 16, 16, 0));
        assertThat(lesson1.getClassroom()).isEqualTo("A102");
        assertThat(lesson1.getDuration()).isEqualTo(90);
        
        Lesson lesson2 = result.getLessons().get(1);
        assertThat(lesson2.getStartDate()).isEqualTo(LocalDateTime.of(2024, 1, 18, 16, 0));
        assertThat(lesson2.getClassroom()).isEqualTo("A102");
        assertThat(lesson2.getDuration()).isEqualTo(60);
    }
}
