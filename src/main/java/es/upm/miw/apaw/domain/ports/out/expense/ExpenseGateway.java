package es.upm.miw.apaw.domain.ports.out.expense;

import es.upm.miw.apaw.domain.model.expense.Expense;

import java.util.UUID;

public interface ExpenseGateway {
    boolean isSupplierInUse(UUID supplierId);
    boolean existsByReference(String reference);
    Expense create(Expense expense);
}