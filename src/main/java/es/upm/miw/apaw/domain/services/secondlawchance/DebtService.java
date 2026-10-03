package es.upm.miw.apaw.domain.services.secondlawchance;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.secondlawchance.Debt;
import es.upm.miw.apaw.domain.model.secondlawchance.DebtPatch;
import es.upm.miw.apaw.domain.model.secondlawchance.SharedDebtReport;
import es.upm.miw.apaw.domain.ports.out.secondlawchance.DebtGateway;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DebtService {
    private final DebtGateway debtGateway;
    private final UserFinder userFinder;

    public Debt create(Debt debt) {
        this.assertContractNumberAvailable(null, debt.getContractNumber());
        debt.doDefault();
        return this.debtGateway.create(debt);
    }

    public List<Debt> findAll() {
        return this.debtGateway.findAll();
    }

    public Debt read(UUID id) {
        return this.debtGateway.read(id)
                .orElseThrow(() -> new NotFoundException("Debt id not found: " + id));
    }

    public Debt update(UUID id, Debt debt) {
        Debt storedDebt = this.read(id);
        this.assertContractNumberAvailable(storedDebt.getContractNumber(), debt.getContractNumber());
        debt.applyDefaults();
        debt.setId(id);
        return this.debtGateway.update(debt);
    }

    public Debt patch(UUID id, DebtPatch patch) {
        Debt storedDebt = this.read(id);
        if (patch.contractNumber() != null) {
            this.assertContractNumberAvailable(storedDebt.getContractNumber(), patch.contractNumber());
            storedDebt.setContractNumber(patch.contractNumber());
        }
        if (patch.issueDate() != null) {
            storedDebt.setIssueDate(patch.issueDate());
        }
        if (patch.creditorName() != null) {
            storedDebt.setCreditorName(patch.creditorName());
        }
        if (patch.amount() != null) {
            storedDebt.setAmount(patch.amount());
        }
        if (patch.type() != null) {
            storedDebt.setType(patch.type());
        }
        if (patch.guarantee() != null) {
            storedDebt.setGuarantee(patch.guarantee());
        }
        return this.debtGateway.update(storedDebt);
    }

    public void delete(UUID id) {
        if (this.debtGateway.isReferenced(id)) {
            throw new ConflictException("Debt is referenced by an exoneration case: " + id);
        }
        this.debtGateway.delete(id);
    }

    public List<SharedDebtReport> findSharedReport() {
        List<SharedDebtReport> report = this.debtGateway.findSharedReport();
        if (report.isEmpty()) {
            return List.of();
        }
        Set<UUID> userIds = report.stream()
                .flatMap(item -> item.getDebtorIds().stream())
                .collect(Collectors.toSet());
        Map<UUID, UserSnapshot> usersById = this.userFinder.findByIds(userIds).stream()
                .collect(Collectors.toMap(UserSnapshot::getId, Function.identity()));
        report.forEach(item -> item.setDebtors(item.getDebtorIds().stream()
                .map(userId -> this.readUser(usersById, userId))
                .toList()));
        return report;
    }

    private UserSnapshot readUser(Map<UUID, UserSnapshot> usersById, UUID userId) {
        UserSnapshot user = usersById.get(userId);
        if (user == null) {
            throw new NotFoundException("User id not found: " + userId);
        }
        return user;
    }

    private void assertContractNumberAvailable(String storedContractNumber, String contractNumber) {
        if (!contractNumber.equals(storedContractNumber)
                && this.debtGateway.existsByContractNumber(contractNumber)) {
            throw new ConflictException("Debt contract number already exists: " + contractNumber);
        }
    }
}
