package es.upm.miw.apaw.domain.ports.out.legalprocedure;

import es.upm.miw.apaw.domain.models.legalprocedure.LegalTask;

public interface LegalTaskGateway {
    LegalTask create(LegalTask legalTask);

    boolean existsByTitle(String title);
}
