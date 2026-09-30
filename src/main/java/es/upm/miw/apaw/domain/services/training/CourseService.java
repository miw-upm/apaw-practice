package es.upm.miw.apaw.domain.services.training;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.model.training.Course;
import es.upm.miw.apaw.domain.ports.out.training.CourseGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseGateway courseGateway;

    public Course create(Course course) {
        if (this.courseGateway.existsByName(course.getName())) {
            throw new ConflictException("Course name already exists: " + course.getName());
        }
        course.doDefault();
        return this.courseGateway.create(course);
    }
}
