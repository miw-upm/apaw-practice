package es.upm.miw.apaw.domain.services.expense;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.expense.CreationExpense;
import es.upm.miw.apaw.domain.model.expense.Expense;
import es.upm.miw.apaw.domain.model.expense.Supplier;
import es.upm.miw.apaw.domain.ports.out.expense.ExpenseGateway;
import es.upm.miw.apaw.domain.ports.out.expense.SupplierGateway;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseGateway expenseGateway;
    private final SupplierGateway supplierGateway;
    private final UserFinder userFinder;

    public Expense create(CreationExpense creation) {
        if (this.expenseGateway.existsByReference(creation.getReference())) {
            throw new ConflictException("Expense reference already exists: " + creation.getReference());
        }
        Expense expense = new Expense();
        BeanUtils.copyProperties(creation, expense);
        expense.setSupplier(this.readSupplier(creation.getSupplierId()));
        expense.setUserSnapshot(this.userFinder.read(creation.getApplicantId()));
        expense.doDefault();
        return this.expenseGateway.create(expense);
    }

    private Supplier readSupplier(UUID id) {
        return this.supplierGateway.readById(id)
                .orElseThrow(() -> new NotFoundException("Supplier id not found: " + id));
    }
}