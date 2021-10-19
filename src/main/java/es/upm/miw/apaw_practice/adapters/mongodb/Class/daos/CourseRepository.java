package es.upm.miw.apaw_practice.adapters.mongodb.Class.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.Class.entities.CourseEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CourseRepository extends MongoRepository<CourseEntity,String> {

    int deleteByName(String name);
}
