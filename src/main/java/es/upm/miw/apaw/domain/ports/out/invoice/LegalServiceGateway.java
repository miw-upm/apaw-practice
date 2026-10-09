package es.upm.miw.apaw.domain.ports.out.invoice;

import es.upm.miw.apaw.domain.model.invoice.LegalService;

public interface LegalServiceGateway {

    LegalService create(LegalService legalService);

    boolean existsByName(String name);
}