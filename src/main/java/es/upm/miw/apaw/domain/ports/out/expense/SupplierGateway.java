package es.upm.miw.apaw.domain.ports.out.expense;

import es.upm.miw.apaw.domain.model.expense.Supplier;

import java.util.Optional;
import java.util.UUID;

public interface SupplierGateway {
    Supplier create(Supplier supplier);
    boolean existsByTaxId(String taxId);
    Optional<Supplier> readById(UUID id);
    Supplier update(Supplier supplier);
    boolean existsByTaxIdAndIdNot(String taxId, UUID id);
    void deleteById(UUID id);
}