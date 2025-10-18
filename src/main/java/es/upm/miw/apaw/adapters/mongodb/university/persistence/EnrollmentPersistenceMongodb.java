package es.upm.miw.apaw.adapters.mongodb.university.persistence;

import es.upm.miw.apaw.adapters.mongodb.university.daos.EnrollmentRepository;
import es.upm.miw.apaw.adapters.mongodb.university.entities.EnrollmentEntity;
import es.upm.miw.apaw.adapters.mongodb.university.entities.SubjectAssignmentEntity;
import es.upm.miw.apaw.domain.models.university.Enrollment;
import es.upm.miw.apaw.domain.models.university.SubjectAssignment;
import es.upm.miw.apaw.domain.persistenceports.university.EnrollmentPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

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

    @Override
    public Stream<Enrollment> findAll() {
        return this.enrollmentRepository.findAll().stream()
                .map(EnrollmentEntity::toEnrollment);
    }
}
