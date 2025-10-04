package es.upm.miw.apaw.domain.persistenceports.university;

import es.upm.miw.apaw.domain.models.university.Subject;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectPersistence {
    Subject create(Subject subject);

    boolean existName(String name);
}
