package es.upm.miw.apaw.domain.services.expense;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.expense.Supplier;
import es.upm.miw.apaw.domain.ports.out.expense.ExpenseGateway;
import es.upm.miw.apaw.domain.ports.out.expense.SupplierGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class SupplierService {

    private final SupplierGateway supplierGateway;
    private final ExpenseGateway expenseGateway;

    @Autowired
    public SupplierService(SupplierGateway supplierGateway, ExpenseGateway expenseGateway) {
        this.supplierGateway = supplierGateway;
        this.expenseGateway = expenseGateway;
    }

    public Supplier create(final Supplier supplier) {
        if (this.supplierGateway.existsByTaxId(supplier.getTaxId())) {
            log.warn("Attempted to create supplier with existing taxId: {}", supplier.getTaxId());
            throw new ConflictException("Supplier taxId already exists: " + supplier.getTaxId());
        }
        supplier.doDefault();
        Supplier created = this.supplierGateway.create(supplier);
        log.info("Successfully created supplier with ID: {}", created.getId());
        return created;
    }

    public Supplier read(final UUID id) {
        log.debug("Reading supplier with ID: {}", id);
        return this.supplierGateway.readById(id)
                .orElseThrow(() -> {
                    log.warn("Supplier not found with ID: {}", id);
                    return new NotFoundException("Supplier id not found: " + id);
                });
    }

    public Supplier update(UUID id, Supplier supplier) {
        Supplier existing = this.read(id);
        if (this.supplierGateway.existsByTaxIdAndIdNot(supplier.getTaxId(), id)) {
            log.warn("Cannot update supplier {}: taxId {} already used by another entity", id, supplier.getTaxId());
            throw new ConflictException("TaxId already exists for another supplier: " + supplier.getTaxId());
        }
        supplier.setId(existing.getId());
        log.info("Updating supplier with ID: {}", id);
        return this.supplierGateway.update(supplier);
    }

    public void delete(UUID id) {
        this.read(id);
        if (this.expenseGateway.isSupplierInUse(id)) {
            log.warn("Cannot delete supplier {}: currently referenced by expenses", id);
            throw new ConflictException("Cannot delete supplier in use by an expense: " + id);
        }
        log.info("Deleting supplier with ID: {}", id);
        this.supplierGateway.deleteById(id);
    }

    public List<Supplier> findAll() {
        log.debug("Fetching all suppliers ordered by companyName");
        return this.supplierGateway.findAll().stream()
                .sorted(Comparator.comparing(Supplier::getCompanyName))
                .toList();
    }
}