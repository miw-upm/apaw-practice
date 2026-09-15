package es.upm.miw.apaw.domain.ports.out.user;

import es.upm.miw.apaw.domain.models.UserSnapshot;

import java.util.Optional;
import java.util.UUID;

public interface UserFinder {
    UserSnapshot read(UUID id);

    Optional<UserSnapshot> findByMobile(String mobile);
}
