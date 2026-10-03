package es.upm.miw.apaw.adapters.out.expense.postgres;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SupplierRepository extends JpaRepository<SupplierEntity, UUID> {
    boolean existsByTaxId(String taxId);
}