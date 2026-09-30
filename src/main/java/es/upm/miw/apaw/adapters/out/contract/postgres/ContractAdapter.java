package es.upm.miw.apaw.adapters.out.contract.postgres;

import es.upm.miw.apaw.domain.ports.out.contract.ContractGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ContractAdapter implements ContractGateway {
    private final ContractRepository contractRepository;
}



