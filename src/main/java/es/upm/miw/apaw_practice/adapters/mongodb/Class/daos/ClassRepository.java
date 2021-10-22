package es.upm.miw.apaw_practice.adapters.mongodb.Class.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.Class.entities.ClassEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClassRepository extends MongoRepository<ClassEntity,String> {
}
