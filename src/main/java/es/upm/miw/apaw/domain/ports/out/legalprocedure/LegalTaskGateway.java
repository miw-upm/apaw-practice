package es.upm.miw.apaw.domain.ports.out.legalprocedure;

import es.upm.miw.apaw.domain.models.legalprocedure.LegalTask;

import java.util.Optional;
import java.util.UUID;

public interface LegalTaskGateway {
    LegalTask create(LegalTask legalTask);

    Optional<LegalTask> read(UUID id);

    LegalTask update(LegalTask legalTask);

    void delete(UUID id);

    boolean isReferenced(UUID id);

    boolean existsByTitle(String title);
}
