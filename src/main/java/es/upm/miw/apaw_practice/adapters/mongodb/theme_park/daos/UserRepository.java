package es.upm.miw.apaw_practice.adapters.mongodb.theme_park.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.theme_park.entities.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserRepository extends MongoRepository<UserEntity, String> {

}
