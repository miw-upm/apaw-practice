package es.upm.miw.apaw_practice.domain.services.course;

import es.upm.miw.apaw_practice.domain.models.course.User;
import es.upm.miw.apaw_practice.domain.persistence_ports.course.UserPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCourseService {

    private final UserPersistence userPersistence;

    @Autowired
    public UserCourseService(UserPersistence userPersistence) {
        this.userPersistence = userPersistence;
    }

    public User create(User user) {
        return this.userPersistence.create(user);
    }

    public List<String> emailsOfTitleTutoringSession(String title) {
        return this.userPersistence.emailsOfTitleTutoringSession(title);
    }
}
