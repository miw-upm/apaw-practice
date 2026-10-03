package es.upm.miw.apaw.domain.services.contract;

import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.contract.Clause;
import es.upm.miw.apaw.domain.model.contract.Contract;
import es.upm.miw.apaw.domain.model.contract.CreationContract;
import es.upm.miw.apaw.domain.ports.out.contract.ClauseGateway;
import es.upm.miw.apaw.domain.ports.out.contract.ContractGateway;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContractService {

    private final ContractGateway contractGateway;
    private final ClauseGateway clauseGateway;
    private final UserFinder userFinder;

    public Contract create(CreationContract creation) {
        Contract contract = new Contract();
        BeanUtils.copyProperties(creation, contract);

        contract.setClauses(creation.getClauseIds().stream()
                .map(this::readClause)
                .toList());

        contract.setUserSnapshot(this.userFinder.read(creation.getUserId()));

        contract.doDefault();

        return this.contractGateway.create(contract);
    }

    private Clause readClause(UUID id) {
        return this.clauseGateway.read(id)
                .orElseThrow(() -> new NotFoundException("Clause id not found: " + id));
    }
}