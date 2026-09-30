package es.upm.miw.apaw.domain.ports.out.contract;

import es.upm.miw.apaw.domain.model.contract.Clause;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClauseGateway {
    Clause create(Clause clause);

    Optional<Clause> read(UUID id);

    Clause update(Clause clause);

    void delete(UUID id);
    boolean isReferenced(UUID id);

    List<Clause> findAll();
}
