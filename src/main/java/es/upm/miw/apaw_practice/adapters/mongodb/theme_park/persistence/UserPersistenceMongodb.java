package es.upm.miw.apaw_practice.adapters.mongodb.theme_park.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.theme_park.daos.UserRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.theme_park.entities.UserEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;

import es.upm.miw.apaw_practice.domain.models.theme_park.User;
import es.upm.miw.apaw_practice.domain.persistence_ports.theme_park.UserPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository("userPersistence")
public class UserPersistenceMongodb implements UserPersistence {

    private final UserRepository userRepository;

    @Autowired
    public UserPersistenceMongodb(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User read(String idMembership) {
        return this.userRepository
                .findByIdMembership(idMembership)
                .orElseThrow(() -> new NotFoundException("User idMembership: " + idMembership))
                .toUser();
    }

    @Override
    public boolean existIdMembership(String idMembership) {
        return this.userRepository
                .findByIdMembership(idMembership)
                .isPresent();
    }

    @Override
    public User create(User user) {
        return this.userRepository
                .save(new UserEntity(user))
                .toUser();
    }

}
