package es.upm.miw.apaw_practice.adapters.mongodb.training.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.training.daos.CourseRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.training.entities.CourseEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.training.Course;
import es.upm.miw.apaw_practice.domain.persistence_ports.training.CoursePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository("CoursePersistence")
public class CoursePersistenceMongodb implements CoursePersistence {
    private final CourseRepository courseRepository;

    @Autowired
    public CoursePersistenceMongodb(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
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
}
