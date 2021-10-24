package es.upm.miw.apaw_practice.domain.persistence_ports.gym;

import es.upm.miw.apaw_practice.domain.models.gym.Lesson;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonPersistence {
    Lesson findByTitle(String title);

    List<String> findGymByTitleAndName(String title, String name);
}
