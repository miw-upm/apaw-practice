package es.upm.miw.apaw_practice.domain.persistence_ports.gym;

import es.upm.miw.apaw_practice.domain.models.gym.Lesson;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonPersistence {
    Lesson findByTitle(String title);
}
