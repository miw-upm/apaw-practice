package es.upm.miw.apaw.domain.ports.out.legalprocedure;

import es.upm.miw.apaw.domain.models.legalprocedure.LegalProcedure;

public interface LegalProcedureGateway {
    LegalProcedure create(LegalProcedure legalProcedure);

    boolean existsByTitle(String title);
}
