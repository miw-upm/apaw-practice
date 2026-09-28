package es.upm.miw.apaw.domain.ports.out.courthearing;

import java.util.Optional;
import java.util.UUID;

import es.upm.miw.apaw.domain.model.courthearing.Court;

public interface CourtGateway {
    Court create(Court court);

    boolean existsByName(String name);

    boolean existsByPhone(String phone);

    Optional<Court> read(UUID id);
}