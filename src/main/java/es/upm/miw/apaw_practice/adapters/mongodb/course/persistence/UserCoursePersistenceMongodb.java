package es.upm.miw.apaw_practice.adapters.mongodb.course.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.course.daos.UserCourseRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.course.entities.UserCourseEntity;
import es.upm.miw.apaw_practice.domain.models.course.User;
import es.upm.miw.apaw_practice.domain.persistence_ports.course.UserPersistence;
import org.springframework.stereotype.Repository;

@Repository("userCoursePersistence")
public class UserCoursePersistenceMongodb implements UserPersistence {

    private final UserCourseRepository userCourseRepository;

    public UserCoursePersistenceMongodb(UserCourseRepository userCourseRepository) {
        this.userCourseRepository = userCourseRepository;
    }

    @Override
    public User create(User user) {
        return this.userCourseRepository.save(new UserCourseEntity(user.getFirstName(), user.getEmail(), user.getRole().name()))
                .toUserCourse();
    }
}
