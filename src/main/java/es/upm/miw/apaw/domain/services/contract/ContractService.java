package es.upm.miw.apaw.domain.services.contract;

import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.contract.*;
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
import java.util.function.Function;
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

    public List<Contract> find(ContractFindCriteria criteria) {
        List<Contract> contracts = this.contractGateway.find(criteria);

        if (contracts.isEmpty()) {
            return List.of();
        }

        Set<UUID> userIds = contracts.stream()
                .map(contract -> contract.getUserSnapshot().getId())
                .collect(Collectors.toSet());

        return this.toSummaries(
                criteria,
                contracts,
                this.userFinder.findByIds(userIds)
        );
    }

    private Clause readClause(UUID id) {
        return this.clauseGateway.read(id)
                .orElseThrow(() -> new NotFoundException("Clause id not found: " + id));
    }

    private List<Contract> toSummaries(
            ContractFindCriteria criteria,
            List<Contract> contracts,
            List<UserSnapshot> users) {

        Map<UUID, UserSnapshot> usersById = users.stream()
                .collect(Collectors.toMap(
                        UserSnapshot::getId,
                        Function.identity()
                ));

        return contracts.stream()
                .map(contract -> this.enrichUserSnapshot(contract, usersById))
                .filter(contract -> this.matchesUserCity(criteria, contract))
                .map(Contract::ofSummary)
                .toList();
    }

    private Contract enrichUserSnapshot(
            Contract contract,
            Map<UUID, UserSnapshot> usersById) {

        UUID userId = contract.getUserSnapshot().getId();
        UserSnapshot user = usersById.get(userId);

        if (user == null) {
            throw new NotFoundException("User id not found: " + userId);
        }

        contract.setUserSnapshot(user);

        return contract;
    }

    private boolean matchesUserCity(
            ContractFindCriteria criteria,
            Contract contract) {

        return !criteria.hasUserCity()
                || criteria.getUserCity()
                .equals(contract.getUserSnapshot().getCity());
    }
}