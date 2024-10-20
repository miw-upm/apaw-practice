package es.upm.miw.apaw_practice.adapters.mongodb.course.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.course.daos.CourseRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.course.daos.TutoringSessionRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.course.entities.CourseEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.course.entities.TutoringSessionEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.persistence_ports.course.TutoringSessionPersistence;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalTime;

@Repository("tutoringSessionPersistence")
public class TutoringSessionPersistenceMongodb implements TutoringSessionPersistence {

    private final TutoringSessionRepository tutoringSessionRepository;
    private final CourseRepository courseRepository;

    public TutoringSessionPersistenceMongodb(TutoringSessionRepository tutoringSessionRepository, CourseRepository courseRepository) {
        this.tutoringSessionRepository = tutoringSessionRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public void delete(String title) {

        if (this.tutoringSessionRepository.existsByTitle(title)){
            this.tutoringSessionRepository.deleteByTitle(title);
        } else {
            throw new NotFoundException("Tuttoring Session: " + title);
        }
    }

    @Override
    public BigDecimal priceSumOfRoleDuration(String role, LocalTime duration) {
        return this.courseRepository.findAll()
                .stream()
                .filter(courseEntity -> courseHasUserWithRole(role, courseEntity))
                .flatMap(courseEntity ->
                        courseEntity.getVideos().stream()
                                .filter(videoEntity -> videoEntity.getDuration().equals(duration))
                                .flatMap(videoEntity -> courseEntity.getTutoringSessions()
                                        .stream()
                                        .map(TutoringSessionEntity::getPrice)
                                )
                )
                .distinct()
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private boolean courseHasUserWithRole(String role, CourseEntity courseEntity) {
        return courseEntity.getUsers()
                .stream()
                .anyMatch(userCourseEntity -> userCourseEntity.getRole().equalsIgnoreCase(role));
    }
}
