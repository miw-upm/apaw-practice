package es.upm.miw.apaw.adapters.out.legalprocedure.postgres;

import es.upm.miw.apaw.domain.ports.out.legalprocedure.LegalProcedureGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LegalProcedureAdapter implements LegalProcedureGateway {
    private final LegalProcedureRepository legalProcedureRepository;
}
