package es.upm.miw.apaw.domain.services.training;

import es.upm.miw.apaw.adapters.out.training.postgres.TrainingPlanEntity;
import es.upm.miw.apaw.adapters.out.training.postgres.TrainingPlanRepository;
import es.upm.miw.apaw.adapters.out.training.postgres.CourseEntity;
import es.upm.miw.apaw.domain.exceptions.BadRequestException;
import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.training.Course;
import es.upm.miw.apaw.domain.model.training.CourseDurationUpdate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
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
    void testDeleteReferencedCourse() {
        Course course = this.createCourse();
        
        assertThatThrownBy(() -> this.courseService.delete(course.getId()))
                .isInstanceOf(ConflictException.class).hasMessageContaining(course.getId().toString());
        
        assertThat(this.courseService.read(course.getId()).getId()).isEqualTo(course.getId());
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
}