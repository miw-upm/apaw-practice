package es.upm.miw.apaw_practice.adapters.mongodb.Class.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.Class.entities.CourseEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface CourseRepository extends MongoRepository<CourseEntity,String> {

    int deleteByName(String name);
    Optional<CourseEntity> findByName(String name);

}
