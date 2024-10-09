package es.upm.miw.apaw_practice.domain.services.theme_park;

import es.upm.miw.apaw_practice.domain.exceptions.ConflictException;
import es.upm.miw.apaw_practice.domain.models.theme_park.User;

import es.upm.miw.apaw_practice.domain.persistence_ports.theme_park.UserPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final UserPersistence userPersistence;

    @Autowired
    public UserService(UserPersistence userPersistence) {
        this.userPersistence = userPersistence;
    }

    public User create(User user) {
        this.assertIdMembershipNotExist(user.getIdMembership());
        return this.userPersistence.create(user);
    }

    public void assertIdMembershipNotExist(String idMembership) {
        if (this.userPersistence.existIdMembership(idMembership)) {
            throw new ConflictException("idMembership exist: " + idMembership);
        }
    }
}
