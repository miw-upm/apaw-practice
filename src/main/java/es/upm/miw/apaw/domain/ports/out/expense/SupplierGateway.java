package es.upm.miw.apaw.domain.ports.out.expense;

import es.upm.miw.apaw.domain.model.expense.Supplier;

public interface SupplierGateway {
    Supplier create(Supplier supplier);
    boolean existsByTaxId(String taxId);
}