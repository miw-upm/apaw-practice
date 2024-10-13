package es.upm.miw.apaw_practice.domain.persistence_ports.theme_park;

import es.upm.miw.apaw_practice.domain.models.theme_park.User;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface UserPersistence {

    User read(String idMembership);

    User create(User user);

    boolean existIdMembership(String idMembership);

    User update(String idMembership, User user);

    Stream<User> readAll();
}
