package es.upm.miw.apaw.domain.ports.out.probate;

import es.upm.miw.apaw.domain.model.probate.Heir;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HeirGateway {
    Heir create(Heir heir);

    List<Heir> findAll();

    Optional<Heir> read(UUID id);

    Heir update(Heir heir);

    boolean existsByNationalId(String nationalId);
}