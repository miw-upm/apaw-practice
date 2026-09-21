package es.upm.miw.apaw.domain.ports.out.user;

import es.upm.miw.apaw.domain.model.UserSnapshot;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface UserFinder {
    UserSnapshot read(UUID id);

    List<UserSnapshot> findByIds(Set<UUID> ids);
}
