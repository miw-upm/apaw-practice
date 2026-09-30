package es.upm.miw.apaw.domain.ports.out.training;

import es.upm.miw.apaw.domain.model.training.Course;

public interface CourseGateway {
    Course create(Course course);
    boolean existsByName(String name);
}
