package es.upm.miw.apaw.adapters.out.contract.postgres;

import es.upm.miw.apaw.domain.model.contract.Contract;
import es.upm.miw.apaw.domain.ports.out.contract.ContractGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class ContractAdapter implements ContractGateway {
    private final ContractRepository contractRepository;

    @Override
    @Transactional
    public Contract create(Contract contract) {
        ContractEntity contractEntity = new ContractEntity(contract);
        return this.contractRepository.save(contractEntity).toDomain();
    }
}