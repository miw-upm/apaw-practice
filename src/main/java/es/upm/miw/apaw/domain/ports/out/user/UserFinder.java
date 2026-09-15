package es.upm.miw.apaw.domain.ports.out.user;

import es.upm.miw.apaw.domain.models.UserSnapshot;

import java.util.UUID;

public interface UserFinder {
    UserSnapshot read(UUID id);
}
