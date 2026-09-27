package es.upm.miw.apaw.domain.ports.out.leases;

import es.upm.miw.apaw.domain.model.leases.Amendment;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AmendmentGateway {
    Amendment create(Amendment amendment);

    List<Amendment> findAll();

    Optional<Amendment> read(UUID id);

    Amendment update(Amendment amendment);

    void delete(UUID id);

    boolean isReferenced(UUID id);
}
