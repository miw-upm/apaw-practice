package es.upm.miw.apaw.domain.services.expense;

import es.upm.miw.apaw.adapters.in.expense.ExpenseCreationDto;
import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.expense.Expense;
import es.upm.miw.apaw.domain.model.expense.Supplier;
import es.upm.miw.apaw.domain.ports.out.expense.ExpenseGateway;
import es.upm.miw.apaw.domain.ports.out.expense.SupplierGateway;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpenseService {

    private final ExpenseGateway expenseGateway;
    private final SupplierGateway supplierGateway;
    private final UserFinder userFinder;

    @Autowired
    public ExpenseService(ExpenseGateway expenseGateway, SupplierGateway supplierGateway, UserFinder userFinder) {
        this.expenseGateway = expenseGateway;
        this.supplierGateway = supplierGateway;
        this.userFinder = userFinder;
    }

    public Expense create(ExpenseCreationDto dto) {
        if (this.expenseGateway.existsByReference(dto.getReference())) {
            throw new ConflictException("Expense reference already exists: " + dto.getReference());
        }

        Supplier supplier = this.supplierGateway.readById(dto.getSupplierId())
                .orElseThrow(() -> new NotFoundException("Supplier not found with id: " + dto.getSupplierId()));

        UserSnapshot applicant = this.userFinder.read(dto.getApplicantId());

        Expense expense = Expense.builder()
                .reference(dto.getReference())
                .amount(dto.getAmount())
                .description(dto.getDescription())
                .category(dto.getCategory())
                .supplier(supplier)
                .userSnapshot(applicant)
                .build();

        expense.doDefault();
        return this.expenseGateway.create(expense);
    }
}