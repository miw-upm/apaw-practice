package es.upm.miw.apaw_practice.domain.persistence_ports.music_lesson;

import java.util.stream.Stream;

import es.upm.miw.apaw_practice.domain.models.music_lesson.Lesson;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonPersistence {

  Stream<Lesson> readAll();

}
