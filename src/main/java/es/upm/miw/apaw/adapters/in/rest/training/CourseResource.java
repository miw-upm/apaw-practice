package es.upm.miw.apaw.adapters.in.rest.training;

import es.upm.miw.apaw.domain.model.training.Course;
import es.upm.miw.apaw.domain.services.training.CourseService;

public class CourseResource {
    public static final String COURSES = "/courses";

    private final CourseService courseService;

    public CourseResource(CourseService courseService) {
        this.courseService = courseService;
    }

    public Course create(Course course) {
        course.doDefault();
        return this.courseService.create(course);
    }
}
