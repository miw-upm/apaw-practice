package es.upm.miw.apaw.domain.ports.out.invoice;

import es.upm.miw.apaw.domain.model.invoice.LegalService;
import java.util.Optional;
import java.util.UUID;

public interface LegalServiceGateway {

    LegalService create(LegalService legalService);

    Optional<LegalService> read(UUID id);

    boolean existsByName(String name);
}