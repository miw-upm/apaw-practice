package es.upm.miw.apaw.domain.persistenceports.university;

import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentPersistence {
    void delete(String code);
}
