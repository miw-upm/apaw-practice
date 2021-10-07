package es.upm.miw.apaw_practice.domain.persistence_ports.university;

import es.upm.miw.apaw_practice.domain.models.university.Subject;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectPersistence {

    Subject readByReference(Integer reference);

    void update(Subject subject);
}
