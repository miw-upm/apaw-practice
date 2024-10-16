package es.upm.miw.apaw_practice.adapters.mongodb.course.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.course.daos.CourseRepository;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.course.Course;
import es.upm.miw.apaw_practice.domain.persistence_ports.course.CoursePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("coursePersistence")
public class CoursePersistenceMongodb implements CoursePersistence {

    private final CourseRepository courseRepository;

    @Autowired
    public CoursePersistenceMongodb(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Course read(String tittle) {
        return this.courseRepository
                .findByTitle(tittle)
                .orElseThrow(() -> new NotFoundException("Course tittle: " + tittle))
                .toCourse();
    }
}
