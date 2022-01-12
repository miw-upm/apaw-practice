package es.upm.miw.apaw_practice.adapters.mongodb.training.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.training.entities.CourseEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CourseRepository extends MongoRepository<CourseEntity, String> {
    Optional<CourseEntity> findByIdentity(String identity);
}