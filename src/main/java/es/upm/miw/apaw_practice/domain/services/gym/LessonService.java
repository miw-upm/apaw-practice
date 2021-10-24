package es.upm.miw.apaw_practice.domain.services.gym;

import es.upm.miw.apaw_practice.domain.models.gym.Lesson;
import es.upm.miw.apaw_practice.domain.persistence_ports.gym.LessonPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonService {
    private final LessonPersistence lessonPersistence;

    @Autowired
    public LessonService(LessonPersistence lessonPersistence) {
        this.lessonPersistence = lessonPersistence;
    }


    public Lesson findBytitle(String title) {
        return this.lessonPersistence.findByTitle(title);
    }

    public List<String> findGymByTitelAndName(String title, String name) {
        return this.lessonPersistence.findGymByTitleAndName(title, name);
    }
}
