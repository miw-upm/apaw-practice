package es.upm.miw.apaw.domain.persistenceports.university;

import es.upm.miw.apaw.domain.models.university.Enrollment;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface EnrollmentPersistence {
    void delete(String code);

    Stream<Enrollment> findAll();
}
