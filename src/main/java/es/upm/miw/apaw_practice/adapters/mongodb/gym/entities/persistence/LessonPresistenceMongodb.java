package es.upm.miw.apaw_practice.adapters.mongodb.gym.entities.persistence;


import es.upm.miw.apaw_practice.adapters.mongodb.gym.entities.daos.LessonRepository;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.gym.Lesson;
import es.upm.miw.apaw_practice.domain.persistence_ports.gym.LessonPersistence;
import org.springframework.stereotype.Repository;


@Repository("LessonPersistence")
public class LessonPresistenceMongodb implements LessonPersistence {
    private final LessonRepository lessonRepository;

    public LessonPresistenceMongodb(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    @Override
    public Lesson findByTitle(String title) {
        return this.lessonRepository.findByTitle(title).
                orElseThrow(() -> new NotFoundException("Lesson with name :" + title)).toLesson();
    }
}
