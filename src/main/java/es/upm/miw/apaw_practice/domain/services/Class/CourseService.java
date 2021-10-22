package es.upm.miw.apaw_practice.domain.services.Class;

import es.upm.miw.apaw_practice.domain.models.Class.Course;
import es.upm.miw.apaw_practice.domain.persistence_ports.Class.CoursePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class CourseService {
    private final CoursePersistence coursePersistence;

    @Autowired
    public CourseService(CoursePersistence coursePersistence) {
        this.coursePersistence = coursePersistence;
    }

    public Stream<Course> readAll() {
        return this.coursePersistence.readAll();
    }

    public void delete(String name) {
        this.coursePersistence.delete(name);
    }
}
