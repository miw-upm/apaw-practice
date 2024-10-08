package es.upm.miw.apaw_practice.adapters.mongodb.music_lesson.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.music_lesson.entities.LessonEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LessonRepository extends MongoRepository<LessonEntity, String> {

}
