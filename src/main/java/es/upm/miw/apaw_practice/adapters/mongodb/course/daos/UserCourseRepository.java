package es.upm.miw.apaw_practice.adapters.mongodb.course.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.course.entities.UserCourseEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserCourseRepository extends MongoRepository<UserCourseEntity, String> {
    Optional<UserCourseEntity> findByEmail(String email);
}
