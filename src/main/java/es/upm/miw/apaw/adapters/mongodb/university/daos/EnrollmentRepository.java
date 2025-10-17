package es.upm.miw.apaw.adapters.mongodb.university.daos;

import es.upm.miw.apaw.adapters.mongodb.university.entities.EnrollmentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface EnrollmentRepository extends MongoRepository<EnrollmentEntity, UUID> {
    int deleteByCode(String code);

    Optional<EnrollmentEntity> findByCode(String code);
}

