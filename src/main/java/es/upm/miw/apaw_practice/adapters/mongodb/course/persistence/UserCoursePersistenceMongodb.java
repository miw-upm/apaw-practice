package es.upm.miw.apaw_practice.adapters.mongodb.course.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.course.daos.CourseRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.course.daos.TutoringSessionRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.course.daos.UserCourseRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.course.entities.TutoringSessionEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.course.entities.UserCourseEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.course.TutoringSession;
import es.upm.miw.apaw_practice.domain.models.course.User;
import es.upm.miw.apaw_practice.domain.persistence_ports.course.UserPersistence;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository("userCoursePersistence")
public class UserCoursePersistenceMongodb implements UserPersistence {

    private final UserCourseRepository userCourseRepository;
    private final CourseRepository courseRepository;
    private final TutoringSessionRepository tutoringSessionRepository;

    public UserCoursePersistenceMongodb(UserCourseRepository userCourseRepository, CourseRepository courseRepository, TutoringSessionRepository tutoringSessionRepository) {
        this.userCourseRepository = userCourseRepository;
        this.courseRepository = courseRepository;
        this.tutoringSessionRepository = tutoringSessionRepository;
    }

    @Override
    public User create(User user) {
        return this.userCourseRepository.save(new UserCourseEntity(user.getFirstName(), user.getEmail(), user.getRole().name()))
                .toUserCourse();
    }

    @Override
    public List<String> emailsOfTitleTutoringSession(String title) {

        this.tutoringSessionRepository.findByTitle(title)
                .orElseThrow(() -> new NotFoundException("No Title: " + title));

        return this.courseRepository.findAll().stream()
                .flatMap(courseEntity -> Optional.ofNullable(courseEntity.getTutoringSessions())
                        .orElseGet(Collections::emptyList).stream() // Si es null, usar lista vacía
                        .filter(tutoringSessionEntity -> tutoringSessionEntity.getTitle().equals(title))
                        .flatMap(tutoringSessionEntity -> courseEntity.getUsers().stream())
                        .map(UserCourseEntity::getEmail)
                )
                .distinct() // Evitar duplicados
                .toList();
    }
}