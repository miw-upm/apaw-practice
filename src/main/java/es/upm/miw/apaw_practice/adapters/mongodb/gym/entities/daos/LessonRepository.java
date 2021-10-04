package es.upm.miw.apaw_practice.adapters.mongodb.gym.entities.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.gym.entities.LessonEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LessonRepository extends MongoRepository<LessonEntity,String> {
}
