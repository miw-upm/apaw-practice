package es.upm.miw.apaw.domain.ports.out.expense;

import java.util.UUID;

public interface ExpenseGateway {
    boolean isSupplierInUse(UUID supplierId);
}