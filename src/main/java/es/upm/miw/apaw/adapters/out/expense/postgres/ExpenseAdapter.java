package es.upm.miw.apaw.adapters.out.expense.postgres;

import es.upm.miw.apaw.domain.ports.out.expense.ExpenseGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class ExpenseAdapter implements ExpenseGateway {

    private final ExpenseRepository expenseRepository;

    @Autowired
    public ExpenseAdapter(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public boolean isSupplierInUse(UUID supplierId) {
        return this.expenseRepository.existsBySupplierEntity_Id(supplierId);
    }
}