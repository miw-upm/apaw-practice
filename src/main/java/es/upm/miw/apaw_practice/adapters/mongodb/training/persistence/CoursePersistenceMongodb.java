package es.upm.miw.apaw_practice.adapters.mongodb.training.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.training.daos.CourseRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.training.daos.TrainingItemRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.training.entities.CourseEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.training.entities.TrainingItemEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.training.Course;
import es.upm.miw.apaw_practice.domain.persistence_ports.training.CoursePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository("CoursePersistence")
public class CoursePersistenceMongodb implements CoursePersistence {
    private final CourseRepository courseRepository;
    private final TrainingItemRepository trainingItemRepository;

    @Autowired
    public CoursePersistenceMongodb(CourseRepository courseRepository, TrainingItemRepository trainingItemRepository) {
        this.courseRepository = courseRepository;
        this.trainingItemRepository = trainingItemRepository;
    }

    @Override
    public Course read(String identity) {
        return this.courseRepository
                .findByIdentity(identity)
                .orElseThrow(() -> new NotFoundException("Course identity: " + identity))
                .toCourse();
    }

    @Override
    public boolean existIdentity(String identity) {
        return this.courseRepository
                .findByIdentity(identity)
                .isPresent();
    }

    @Override
    public Stream<Course> readAll() {
        return this.courseRepository
                .findAll().stream()
                .map(CourseEntity::toCourse);
    }

    @Override
    public Course create(Course course) {
        return this.courseRepository
                .save(new CourseEntity(course))
                .toCourse();
    }

    @Override
    public Course update(String identity, Course course) {
        CourseEntity courseEntity = this.courseRepository
                .findByIdentity(course.getIdentity())
                .orElseThrow(() -> new NotFoundException("Course identity: " + course.getIdentity()));
        courseEntity.fromCourse(course);
        return this.courseRepository
                .save(courseEntity)
                .toCourse();
    }

    @Override
    public BigDecimal findCoursePriceSumByLecturerStartDate(LocalDate date) {
        return trainingItemRepository.findAll().stream()
                .filter(trainingItemEntity ->
                        trainingItemEntity.getLecturerList().stream().anyMatch(lecturerEntity ->
                                lecturerEntity.getStartDate().isAfter(date)))
                .collect(Collectors.groupingBy(TrainingItemEntity::getCourseEntity)).keySet()
                .stream().map(CourseEntity::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
