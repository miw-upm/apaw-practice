package es.upm.miw.apaw.adapters.mongodb.university.persistence;

import es.upm.miw.apaw.adapters.mongodb.university.daos.EnrollmentRepository;
import es.upm.miw.apaw.domain.persistenceports.university.EnrollmentPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EnrollmentPersistenceMongodb implements EnrollmentPersistence {

    private final EnrollmentRepository enrollmentRepository;

    @Autowired
    public EnrollmentPersistenceMongodb(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    @Override
    public void delete(String code) {
        this.enrollmentRepository.deleteByCode(code);
    }
}
