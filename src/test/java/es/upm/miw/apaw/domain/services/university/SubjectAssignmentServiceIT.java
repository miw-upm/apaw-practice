package es.upm.miw.apaw.domain.services.university;

import es.upm.miw.apaw.adapters.mongodb.university.daos.SubjectAssignmentRepository;
import es.upm.miw.apaw.adapters.mongodb.university.entities.SubjectAssignmentEntity;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.university.Lesson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
class SubjectAssignmentServiceIT {

    @Autowired
    private SubjectAssignmentService subjectAssignmentService;

    @Autowired
    private SubjectAssignmentRepository subjectAssignmentRepository;

    @Test
    void testGetLessons() {
        UUID subjectAssignmentId = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0304");
        
        Optional<SubjectAssignmentEntity> subjectAssignmentEntity = subjectAssignmentRepository.findById(subjectAssignmentId);
        assertThat(subjectAssignmentEntity).isPresent();
        
        List<Lesson> lessons = subjectAssignmentService.getLessons(subjectAssignmentId);
        
        assertThat(lessons).isNotEmpty();
        assertThat(lessons).hasSize(2);
        
        Lesson lesson1 = lessons.get(0);
        assertThat(lesson1.getStartDate()).isEqualTo(LocalDateTime.of(2024, 1, 19, 10, 0));
        assertThat(lesson1.getClassroom()).isEqualTo("D401");
        assertThat(lesson1.getDuration()).isEqualTo(90);
        
        Lesson lesson2 = lessons.get(1);
        assertThat(lesson2.getStartDate()).isEqualTo(LocalDateTime.of(2024, 1, 21, 10, 0));
        assertThat(lesson2.getClassroom()).isEqualTo("D401");
        assertThat(lesson2.getDuration()).isEqualTo(90);
    }

    @Test
    void testGetLessonsNotFound() {
        UUID nonExistentId = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff9999");
        
        Optional<SubjectAssignmentEntity> subjectAssignmentEntity = subjectAssignmentRepository.findById(nonExistentId);
        assertThat(subjectAssignmentEntity).isEmpty();
        
        assertThatThrownBy(() -> subjectAssignmentService.getLessons(nonExistentId))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("SubjectAssignment id: " + nonExistentId);
    }

    @Test
    void testGetLessonsWithMultipleLessons() {
        UUID subjectAssignmentId = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0305");
        
        Optional<SubjectAssignmentEntity> subjectAssignmentEntity = subjectAssignmentRepository.findById(subjectAssignmentId);
        assertThat(subjectAssignmentEntity).isPresent();
        
        List<Lesson> lessons = subjectAssignmentService.getLessons(subjectAssignmentId);
        
        assertThat(lessons).isNotEmpty();
        assertThat(lessons).hasSize(1);
        
        Lesson lesson = lessons.get(0);
        assertThat(lesson.getStartDate()).isEqualTo(LocalDateTime.of(2024, 1, 20, 15, 0));
        assertThat(lesson.getClassroom()).isEqualTo("E501");
        assertThat(lesson.getDuration()).isEqualTo(120);
    }

    @Test
    void testGetLessonsWithSingleLesson() {
        UUID subjectAssignmentId = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0300");
        
        Optional<SubjectAssignmentEntity> subjectAssignmentEntity = subjectAssignmentRepository.findById(subjectAssignmentId);
        assertThat(subjectAssignmentEntity).isPresent();
        
        List<Lesson> lessons = subjectAssignmentService.getLessons(subjectAssignmentId);
        
        assertThat(lessons).isNotEmpty();
        assertThat(lessons).hasSize(2);
        
        Lesson lesson1 = lessons.get(0);
        assertThat(lesson1.getStartDate()).isEqualTo(LocalDateTime.of(2024, 1, 15, 9, 0));
        assertThat(lesson1.getClassroom()).isEqualTo("A101");
        assertThat(lesson1.getDuration()).isEqualTo(90);
        
        Lesson lesson2 = lessons.get(1);
        assertThat(lesson2.getStartDate()).isEqualTo(LocalDateTime.of(2024, 1, 17, 9, 0));
        assertThat(lesson2.getClassroom()).isEqualTo("A101");
        assertThat(lesson2.getDuration()).isEqualTo(90);
    }
}
