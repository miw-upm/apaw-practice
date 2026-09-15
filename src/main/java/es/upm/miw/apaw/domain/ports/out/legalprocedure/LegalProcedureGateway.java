package es.upm.miw.apaw.domain.ports.out.legalprocedure;

import es.upm.miw.apaw.domain.models.legalprocedure.LegalProcedure;
import es.upm.miw.apaw.domain.models.legalprocedure.LegalProcedureFindCriteria;

import java.util.List;

public interface LegalProcedureGateway {
    LegalProcedure create(LegalProcedure legalProcedure);

    List<LegalProcedure> find(LegalProcedureFindCriteria criteria);

    boolean existsByTitle(String title);
}
