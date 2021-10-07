package es.upm.miw.apaw_practice.adapters.mongodb.Class.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.Class.entities.StudentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<StudentEntity,String> {
}
