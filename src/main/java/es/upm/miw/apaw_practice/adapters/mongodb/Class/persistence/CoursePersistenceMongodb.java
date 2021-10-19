package es.upm.miw.apaw_practice.adapters.mongodb.Class.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.Class.daos.CourseRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.Class.entities.CourseEntity;
import es.upm.miw.apaw_practice.domain.persistence_ports.Class.CoursePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository("CoursePersistence")
public class CoursePersistenceMongodb implements CoursePersistence {

    private final CourseRepository courseRepository;

    @Autowired
    public CoursePersistenceMongodb(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }

    @Override
    public Stream<es.upm.miw.apaw_practice.domain.models.Class.Course> readAll() {
        return this.courseRepository.findAll().stream()
                .map(CourseEntity::toCourse);
    }

    @Override
    public void delete(String name){
        this.courseRepository.deleteByName(name);
    }
}
