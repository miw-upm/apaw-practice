package es.upm.miw.apaw.domain.ports.out.training;

import es.upm.miw.apaw.domain.model.training.Course;

import java.util.Optional;
import java.util.UUID;

public interface CourseGateway {
    Course create(Course course);
    boolean existsByName(String name);
    Optional<Course> read(UUID id);
    Course update(Course course);
    void delete(UUID id);
    boolean isReferenced(UUID id);
}
