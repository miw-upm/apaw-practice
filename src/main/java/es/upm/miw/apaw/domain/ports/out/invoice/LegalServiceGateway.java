package es.upm.miw.apaw.domain.ports.out.invoice;

import es.upm.miw.apaw.domain.model.invoice.LegalService;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LegalServiceGateway {

    LegalService create(LegalService legalService);

    List<LegalService> findAll();

    Optional<LegalService> read(UUID id);

    LegalService update(LegalService legalService);

    void delete(UUID id);

    boolean isReferenced(UUID id);

    boolean existsByName(String name);
}