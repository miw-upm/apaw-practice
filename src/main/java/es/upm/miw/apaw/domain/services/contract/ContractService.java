package es.upm.miw.apaw.domain.services.contract;

import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.contract.Clause;
import es.upm.miw.apaw.domain.model.contract.Contract;
import es.upm.miw.apaw.domain.model.contract.ContractExpirationReport;
import es.upm.miw.apaw.domain.model.contract.CreationContract;
import es.upm.miw.apaw.domain.ports.out.contract.ClauseGateway;
import es.upm.miw.apaw.domain.ports.out.contract.ContractGateway;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

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

    public List<ContractExpirationReport> findExpirationReport() {
        LocalDate today = LocalDate.now();
        LocalDate limitDate = today.plusDays(30);

        List<ContractExpirationReport> reports =
                this.contractGateway.findExpirationReport(today, limitDate);

        Set<UUID> userIds = reports.stream()
                .map(ContractExpirationReport::userId)
                .collect(Collectors.toSet());

        Map<UUID, UserSnapshot> userSnapshots = this.userFinder.findByIds(userIds)
                .stream()
                .collect(Collectors.toMap(
                        UserSnapshot::getId,
                        userSnapshot -> userSnapshot
                ));

        return reports.stream()
                .map(report -> new ContractExpirationReport(
                        report.userId(),
                        userSnapshots.get(report.userId()),
                        report.expiringContractCount(),
                        report.activeClauseCount()
                ))
                .toList();
    }

    private Clause readClause(UUID id) {
        return this.clauseGateway.read(id)
                .orElseThrow(() -> new NotFoundException("Clause id not found: " + id));
    }
}