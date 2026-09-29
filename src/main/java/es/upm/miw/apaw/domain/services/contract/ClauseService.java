package es.upm.miw.apaw.domain.services.contract;

import es.upm.miw.apaw.domain.model.contract.Clause;
import es.upm.miw.apaw.domain.ports.out.contract.ClauseGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClauseService {

    private final ClauseGateway clauseGateway;

    public Clause create(Clause clause) {
        clause.doDefault();
        return this.clauseGateway.create(clause);
    }
}