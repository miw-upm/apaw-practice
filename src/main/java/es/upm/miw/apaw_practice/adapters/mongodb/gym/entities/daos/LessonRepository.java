package es.upm.miw.apaw_practice.adapters.mongodb.gym.entities.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.gym.entities.LessonEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface LessonRepository extends MongoRepository<LessonEntity, String> {
    Optional<LessonEntity> findByTitle(String title);
}
