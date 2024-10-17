package es.upm.miw.apaw_practice.domain.persistence_ports.course;

import es.upm.miw.apaw_practice.domain.models.course.Course;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursePersistence {
    Course read(String tittle);
}
