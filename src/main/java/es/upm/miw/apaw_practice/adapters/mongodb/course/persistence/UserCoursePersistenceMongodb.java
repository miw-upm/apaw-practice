package es.upm.miw.apaw_practice.adapters.mongodb.course.persistence;

import es.upm.miw.apaw_practice.domain.models.course.User;
import es.upm.miw.apaw_practice.domain.persistence_ports.course.UserPersistence;
import org.springframework.stereotype.Repository;

@Repository("userCoursePersistence")
public class UserCoursePersistenceMongodb implements UserPersistence {

    @Override
    public User create(User user) {
        return null;
    }
}
