package es.upm.miw.apaw_practice.adapters.mongodb.course.persistence;

import es.upm.miw.apaw_practice.domain.models.course.Course;
import es.upm.miw.apaw_practice.domain.persistence_ports.course.CoursePersistence;
import org.springframework.stereotype.Repository;

@Repository("coursePersistence")
public class CoursePersistenceMongodb implements CoursePersistence {


    @Override
    public Course read(String tittle) {
        return null;
    }
}
