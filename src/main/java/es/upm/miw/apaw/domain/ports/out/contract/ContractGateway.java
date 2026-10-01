package es.upm.miw.apaw.domain.ports.out.contract;

import es.upm.miw.apaw.domain.model.contract.Contract;

public interface ContractGateway {
    Contract create(Contract contract);
}
