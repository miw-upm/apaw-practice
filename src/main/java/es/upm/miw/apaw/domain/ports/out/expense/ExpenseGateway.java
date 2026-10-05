package es.upm.miw.apaw.domain.ports.out.expense;

import es.upm.miw.apaw.domain.model.expense.Expense;
import es.upm.miw.apaw.domain.model.expense.ExpenseFindCriteria;

import java.util.List;
import java.util.UUID;

public interface ExpenseGateway {
    Expense create(Expense expense);

    boolean existsByReference(String reference);

    boolean isSupplierInUse(UUID supplierId);

    List<Expense> find(ExpenseFindCriteria criteria);
}