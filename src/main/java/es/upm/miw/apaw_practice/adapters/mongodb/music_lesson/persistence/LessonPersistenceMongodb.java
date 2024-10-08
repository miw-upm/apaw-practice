package es.upm.miw.apaw_practice.adapters.mongodb.music_lesson.persistence;

import java.util.stream.Stream;

import es.upm.miw.apaw_practice.adapters.mongodb.music_lesson.daos.LessonRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.music_lesson.entities.LessonEntity;
import es.upm.miw.apaw_practice.domain.models.music_lesson.Lesson;
import es.upm.miw.apaw_practice.domain.persistence_ports.music_lesson.LessonPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("lessonPersistence")
public class LessonPersistenceMongodb implements LessonPersistence {

  private final LessonRepository lessonRepository;

  @Autowired
  public LessonPersistenceMongodb(LessonRepository lessonRepository) {
    this.lessonRepository = lessonRepository;
  }

  @Override
  public Stream<Lesson> readAll() {
    return this.lessonRepository.findAll()
        .stream()
        .map(LessonEntity::toLesson);
  }

}
