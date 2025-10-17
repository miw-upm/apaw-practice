package es.upm.miw.apaw.domain.services.university;

import es.upm.miw.apaw.domain.persistenceports.university.EnrollmentPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentService {
    private final EnrollmentPersistence enrollmentPersistence;

    @Autowired
    public EnrollmentService(EnrollmentPersistence enrollmentPersistence) {
        this.enrollmentPersistence = enrollmentPersistence;
    }

    public void delete(String code) {
        this.enrollmentPersistence.delete(code);
    }
}
