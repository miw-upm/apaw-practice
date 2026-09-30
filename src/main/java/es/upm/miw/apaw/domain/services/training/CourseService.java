package es.upm.miw.apaw.domain.services.training;

import es.upm.miw.apaw.domain.model.training.Course;
import es.upm.miw.apaw.domain.ports.out.training.CourseGateway;

public class CourseService {
    private final CourseGateway courseGateway;

    public CourseService(CourseGateway courseGateway) {
        this.courseGateway = courseGateway;
    }

    public Course create(Course course) {
        // AI Mistake: No conflict exception check
        return this.courseGateway.create(course);
    }
}
