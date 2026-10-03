package es.upm.miw.apaw.domain.services.training;

import es.upm.miw.apaw.adapters.out.training.postgres.CourseEntity;
import es.upm.miw.apaw.adapters.out.training.postgres.TrainingPlanEntity;
import es.upm.miw.apaw.adapters.out.training.postgres.TrainingPlanRepository;
import es.upm.miw.apaw.domain.exceptions.BadRequestException;
import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.training.Course;
import es.upm.miw.apaw.domain.model.training.CourseDurationUpdate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.TrainingSeederForDev.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
class CourseServiceIT {

    @Autowired
    private CourseService courseService;

    @Autowired
    private TrainingPlanRepository trainingPlanRepository;

    @Test
    void testReadSeeder() {
        assertThat(this.courseService.read(COURSE_ID_0)).usingRecursiveComparison().isEqualTo(COURSE_0);
    }

    @Test
    void testReadNotFound() {
        UUID id = UUID.randomUUID();
        assertThatThrownBy(() -> this.courseService.read(id))
                .isInstanceOf(NotFoundException.class).hasMessageContaining(id.toString());
    }

    @Test
    void testFindAllAllowsAdditionalCourses() {
        Course extra = this.createCourse();
        List<Course> courses = this.courseService.findAll();
        assertThat(courses).extracting(Course::getId).contains(COURSE_ID_0, COURSE_ID_1, COURSE_ID_2, COURSE_ID_3, extra.getId());
    }

    @Test
    void testCreate() {
        Course course = this.createCourse();
        Course stored = this.courseService.read(course.getId());
        assertThat(stored).usingRecursiveComparison().ignoringFields("launchDate").isEqualTo(course);
        assertThat(stored.getOnline()).isTrue();
    }

    @Test
    void testCreateDuplicateName() {
        Course course = Course.builder().name(COURSE_0.getName()).build();
        assertThatThrownBy(() -> this.courseService.create(course))
                .isInstanceOf(ConflictException.class).hasMessageContaining(COURSE_0.getName());
    }

    @Test
    void testUpdateReplacesMutableFields() {
        Course original = this.createCourse();
        Course replacement = Course.builder()
                .name("Updated " + UUID.randomUUID())
                .durationHours(99)
                .online(false)
                .certificateReference("UPD-REF")
                .launchDate(LocalDate.of(2028, 1, 1))
                .build();
        this.courseService.update(original.getId(), replacement);
        Course updated = this.courseService.read(original.getId());
        assertThat(updated.getName()).isEqualTo(replacement.getName());
        assertThat(updated.getDurationHours()).isEqualTo(99);
        assertThat(updated.getOnline()).isFalse();
        assertThat(updated.getCertificateReference()).isEqualTo("UPD-REF");
        assertThat(updated.getId()).isEqualTo(original.getId());
    }

    @Test
    void testUpdateSameName() {
        Course course = this.createCourse();
        course.setDurationHours(88);
        this.courseService.update(course.getId(), course);
        assertThat(this.courseService.read(course.getId()).getDurationHours()).isEqualTo(88);
    }

    @Test
    void testUpdateNotFound() {
        UUID id = UUID.randomUUID();
        assertThatThrownBy(() -> this.courseService.update(id, COURSE_0))
                .isInstanceOf(NotFoundException.class).hasMessageContaining(id.toString());
    }

    @Test
    void testUpdateDuplicateNameLeavesCourseUnchanged() {
        Course course = this.createCourse();
        assertThatThrownBy(() -> this.courseService.update(course.getId(), COURSE_0))
                .isInstanceOf(ConflictException.class).hasMessageContaining(COURSE_0.getName());
        assertThat(this.courseService.read(course.getId()).getName()).isEqualTo(course.getName());
    }

    @Test
    void testDelete() {
        Course course = this.createCourse();
        this.courseService.delete(course.getId());
        assertThatThrownBy(() -> this.courseService.read(course.getId())).isInstanceOf(NotFoundException.class);
    }

    @Test
    void testDeleteMissingCourse() {
        UUID id = UUID.randomUUID();
        this.courseService.delete(id);
        assertThatThrownBy(() -> this.courseService.read(id)).isInstanceOf(NotFoundException.class);
    }

    @Test
    void testDeleteReferencedCourse() {
        Course course = this.createCourse();
        TrainingPlanEntity plan = TrainingPlanEntity.builder().id(UUID.randomUUID())
                .planCode("PLAN-" + UUID.randomUUID()).approvalDate(LocalDate.now())
                .courses(List.of(new CourseEntity(course))).userIds(List.of()).build();
        this.trainingPlanRepository.saveAndFlush(plan);
        
        assertThatThrownBy(() -> this.courseService.delete(course.getId()))
                .isInstanceOf(ConflictException.class).hasMessageContaining(course.getId().toString());
        
        assertThat(this.courseService.read(course.getId()).getId()).isEqualTo(course.getId());
        assertThat(this.trainingPlanRepository.existsByCoursesId(course.getId())).isTrue();
    }

    @Test
    void testUpdateCourseDurationHours() {
        Course first = this.createCourse();
        Course second = this.createCourse();
        this.courseService.updateDurationHours(List.of(
                new CourseDurationUpdate(first.getId(), 100),
                new CourseDurationUpdate(second.getId(), 200)));
        assertThat(this.courseService.read(first.getId()).getDurationHours()).isEqualTo(100);
        assertThat(this.courseService.read(second.getId()).getDurationHours()).isEqualTo(200);
    }

    @Test
    void testUpdateCourseDurationHoursNotFoundChangesNothing() {
        Course course = this.createCourse();
        UUID missingId = UUID.randomUUID();
        assertThatThrownBy(() -> this.courseService.updateDurationHours(List.of(
                new CourseDurationUpdate(course.getId(), 100),
                new CourseDurationUpdate(missingId, 200))))
                .isInstanceOf(NotFoundException.class).hasMessageContaining(missingId.toString());
        assertThat(this.courseService.read(course.getId()).getDurationHours()).isNotEqualTo(100);
    }

    @Test
    void testUpdateCourseDurationHoursDuplicateIdChangesNothing() {
        Course course = this.createCourse();
        assertThatThrownBy(() -> this.courseService.updateDurationHours(List.of(
                new CourseDurationUpdate(course.getId(), 100),
                new CourseDurationUpdate(course.getId(), 200))))
                .isInstanceOf(BadRequestException.class).hasMessageContaining(course.getId().toString());
        assertThat(this.courseService.read(course.getId()).getDurationHours()).isNotEqualTo(100);
    }

    private Course createCourse() {
        return this.courseService.create(Course.builder()
                .name("IT Course " + UUID.randomUUID())
                .certificateReference("REF-" + UUID.randomUUID())
                .durationHours(COURSE_0.getDurationHours())
                .online(true)
                .launchDate(LocalDate.now())
                .build());
    }

    @org.junit.jupiter.api.Test
    void testFindModalityReport() {
        java.util.List<es.upm.miw.apaw.domain.model.training.TrainingModalityReport> reports = this.courseService.findModalityReport();
        org.junit.jupiter.api.Assertions.assertFalse(reports.isEmpty());
        org.junit.jupiter.api.Assertions.assertNotNull(reports.get(0).getPlanCount());
    }
}