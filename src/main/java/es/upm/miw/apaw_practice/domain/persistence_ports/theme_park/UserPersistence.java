package es.upm.miw.apaw_practice.domain.persistence_ports.theme_park;

import es.upm.miw.apaw_practice.domain.models.theme_park.User;
import org.springframework.stereotype.Repository;


@Repository
public interface UserPersistence {

    User read(String barcode);

    User create(User user);

    boolean existIdMembership(String idMembership);
}
