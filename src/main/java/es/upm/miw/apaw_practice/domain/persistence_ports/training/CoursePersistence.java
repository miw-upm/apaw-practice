package es.upm.miw.apaw_practice.domain.persistence_ports.training;

import es.upm.miw.apaw_practice.domain.models.training.Course;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursePersistence {

    Course read(String identity);

    boolean existIdentity(String identity);
}
