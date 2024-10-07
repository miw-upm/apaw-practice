package es.upm.miw.apaw_practice.adapters.mongodb.university.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.university.entities.StudentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface StudentRepository extends MongoRepository<StudentEntity, String> {
    Optional<StudentEntity> findByEmail(String email);
}
