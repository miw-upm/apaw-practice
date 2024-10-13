package es.upm.miw.apaw_practice.adapters.mongodb.course.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.course.entities.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserEntity, String> {
}
