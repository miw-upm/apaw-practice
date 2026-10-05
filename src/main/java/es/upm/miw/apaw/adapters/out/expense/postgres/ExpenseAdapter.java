package es.upm.miw.apaw.adapters.out.expense.postgres;

import es.upm.miw.apaw.domain.model.expense.Expense;
import es.upm.miw.apaw.domain.ports.out.expense.ExpenseGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ExpenseAdapter implements ExpenseGateway {

    private final ExpenseRepository expenseRepository;
    private final SupplierRepository supplierRepository;

    @Override
    @Transactional
    public Expense create(Expense expense) {
        ExpenseEntity expenseEntity = new ExpenseEntity(expense);
        SupplierEntity supplierEntity = this.supplierRepository.getReferenceById(expense.getSupplier().getId());
        expenseEntity.setSupplierEntity(supplierEntity);
        this.expenseRepository.save(expenseEntity);
        return expense;
    }

    @Override
    public boolean existsByReference(String reference) {
        return this.expenseRepository.existsByReference(reference);
    }

    @Override
    public boolean isSupplierInUse(UUID supplierId) {
        return this.expenseRepository.existsBySupplierEntity_Id(supplierId);
    }
}