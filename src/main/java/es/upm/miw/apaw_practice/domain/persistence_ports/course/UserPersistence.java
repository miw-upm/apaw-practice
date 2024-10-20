package es.upm.miw.apaw_practice.domain.persistence_ports.course;

import es.upm.miw.apaw_practice.domain.models.course.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPersistence {
    User create(User user);
    List<String> emailsOfTitleTutoringSession(String title);
}
