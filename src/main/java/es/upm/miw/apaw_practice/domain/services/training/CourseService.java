package es.upm.miw.apaw_practice.domain.services.training;

import es.upm.miw.apaw_practice.domain.exceptions.ConflictException;
import es.upm.miw.apaw_practice.domain.models.training.Course;
import es.upm.miw.apaw_practice.domain.persistence_ports.training.CoursePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    private final CoursePersistence coursePersistence;

    @Autowired
    public CourseService(CoursePersistence coursePersistence) {
        this.coursePersistence = coursePersistence;
    }

    public Course create(Course course) {
        this.assertIdentityNotExist(course.getIdentity());
        return this.coursePersistence.create(course);
    }

    private void assertIdentityNotExist(String identity) {
        if (this.coursePersistence.existIdentity(identity)) {
            throw new ConflictException("Identity exist: " + identity);
        }
    }
}
