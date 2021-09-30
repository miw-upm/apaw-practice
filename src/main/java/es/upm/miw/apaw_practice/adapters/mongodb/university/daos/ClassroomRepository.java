package es.upm.miw.apaw_practice.adapters.mongodb.university.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.university.entities.ClassroomEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClassroomRepository extends MongoRepository<ClassroomEntity, String> {
}
