package es.upm.miw.apaw.adapters.out.contract.postgres;

import es.upm.miw.apaw.domain.model.contract.Contract;
import es.upm.miw.apaw.domain.model.contract.ContractExpirationReport;
import es.upm.miw.apaw.domain.ports.out.contract.ContractGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ContractAdapter implements ContractGateway {
    private final ContractRepository contractRepository;
    private final ClauseRepository clauseRepository;

    @Override
    @Transactional
    public Contract create(Contract contract) {
        ContractEntity contractEntity = new ContractEntity(contract);

        List<ClauseEntity> clauseEntities = contract.getClauses().stream()
                .map(clause -> this.clauseRepository.getReferenceById(clause.getId()))
                .collect(Collectors.toCollection(ArrayList::new));

        contractEntity.setClauses(clauseEntities);

        return this.contractRepository.save(contractEntity).toDomain();
    }

    @Override
    public List<ContractExpirationReport> findExpirationReport(LocalDate today, LocalDate limitDate) {
        return this.contractRepository.findExpirationReport(today, limitDate);
    }
}