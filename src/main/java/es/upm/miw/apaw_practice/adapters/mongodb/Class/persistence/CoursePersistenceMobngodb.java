package es.upm.miw.apaw_practice.adapters.mongodb.Class.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.Class.daos.CourseRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.Class.entities.CourseEntity;
import es.upm.miw.apaw_practice.domain.persistence_ports.Class.CoursePersistence;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Stream;

public class CoursePersistenceMobngodb implements CoursePersistence {

    private final CourseRepository courseRepository;

    @Autowired
    public CoursePersistenceMobngodb(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }

    @Override
    public Stream<es.upm.miw.apaw_practice.domain.models.Class.Course> readAll() {
        return this.courseRepository.findAll().stream()
                .map(CourseEntity::toCourse);
    }
}
