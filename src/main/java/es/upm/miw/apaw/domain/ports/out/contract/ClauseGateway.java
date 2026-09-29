package es.upm.miw.apaw.domain.ports.out.contract;

import es.upm.miw.apaw.domain.model.contract.Clause;

public interface ClauseGateway {
    Clause create(Clause clause);
}
