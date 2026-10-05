package es.upm.miw.apaw.adapters.out.expense.postgres;

import es.upm.miw.apaw.domain.model.expense.Supplier;
import es.upm.miw.apaw.domain.model.expense.SupplierExpenseReport;
import es.upm.miw.apaw.domain.ports.out.expense.SupplierGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class SupplierAdapter implements SupplierGateway {

    private final SupplierRepository supplierRepository;
    private final ExpenseRepository expenseRepository;

    @Override
    public Supplier create(Supplier supplier) {
        SupplierEntity entity = new SupplierEntity(supplier);
        SupplierEntity saved = this.supplierRepository.save(entity);
        return saved.toDomain();
    }

    @Override
    public boolean existsByTaxId(String taxId) {
        return this.supplierRepository.existsByTaxId(taxId);
    }

    @Override
    public Optional<Supplier> readById(UUID id) {
        return this.supplierRepository.findById(id)
                .map(SupplierEntity::toDomain);
    }

    @Override
    public Supplier update(Supplier supplier) {
        SupplierEntity entity = new SupplierEntity(supplier);
        SupplierEntity saved = this.supplierRepository.save(entity);
        return saved.toDomain();
    }

    @Override
    public boolean existsByTaxIdAndIdNot(String taxId, UUID id) {
        return this.supplierRepository.existsByTaxIdAndIdNot(taxId, id);
    }

    @Override
    public void deleteById(UUID id) {
        this.supplierRepository.deleteById(id);
    }

    @Override
    public List<Supplier> findAll() {
        return this.supplierRepository.findAll().stream()
                .map(SupplierEntity::toDomain)
                .toList();
    }

    @Override
    public List<SupplierExpenseReport> findExpenseReport() {
        return this.expenseRepository.findSupplierExpenseReport();
    }
}