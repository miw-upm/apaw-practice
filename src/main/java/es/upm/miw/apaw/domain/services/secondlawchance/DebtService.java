package es.upm.miw.apaw.domain.services.secondlawchance;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.secondlawchance.Debt;
import es.upm.miw.apaw.domain.model.secondlawchance.DebtPatch;
import es.upm.miw.apaw.domain.ports.out.secondlawchance.DebtGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DebtService {
    private final DebtGateway debtGateway;

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

    private void assertContractNumberAvailable(String storedContractNumber, String contractNumber) {
        if (!contractNumber.equals(storedContractNumber)
                && this.debtGateway.existsByContractNumber(contractNumber)) {
            throw new ConflictException("Debt contract number already exists: " + contractNumber);
        }
    }
}
