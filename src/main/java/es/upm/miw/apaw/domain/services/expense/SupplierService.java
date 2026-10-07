package es.upm.miw.apaw.domain.services.expense;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.expense.Supplier;
import es.upm.miw.apaw.domain.model.expense.SupplierExpenseReport;
import es.upm.miw.apaw.domain.ports.out.expense.ExpenseGateway;
import es.upm.miw.apaw.domain.ports.out.expense.SupplierGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SupplierService {

    private final SupplierGateway supplierGateway;
    private final ExpenseGateway expenseGateway;

    public Supplier create(Supplier supplier) {
        if (this.supplierGateway.existsByTaxId(supplier.getTaxId())) {
            throw new ConflictException("Supplier taxId already exists: " + supplier.getTaxId());
        }
        supplier.doDefault();
        return this.supplierGateway.create(supplier);
    }

    public Supplier read(UUID id) {
        return this.supplierGateway.readById(id)
                .orElseThrow(() -> new NotFoundException("Supplier id not found: " + id));
    }

    public Supplier update(UUID id, Supplier supplier) {
        Supplier existing = this.read(id);
        if (this.supplierGateway.existsByTaxIdAndIdNot(supplier.getTaxId(), id)) {
            throw new ConflictException("TaxId already exists for another supplier: " + supplier.getTaxId());
        }
        supplier.setId(existing.getId());
        return this.supplierGateway.update(supplier);
    }

    public void delete(UUID id) {
        this.read(id);
        if (this.expenseGateway.isSupplierInUse(id)) {
            throw new ConflictException("Cannot delete supplier in use by an expense: " + id);
        }
        this.supplierGateway.deleteById(id);
    }

    public List<Supplier> findAll() {
        return this.supplierGateway.findAll().stream()
                .sorted(Comparator.comparing(Supplier::getCompanyName))
                .toList();
    }

    public Supplier patch(UUID id, Supplier patchSupplier) {
        Supplier supplier = this.read(id);
        if (patchSupplier.getCompanyName() != null) {
            supplier.setCompanyName(patchSupplier.getCompanyName());
        }
        if (patchSupplier.getAddress() != null) {
            supplier.setAddress(patchSupplier.getAddress());
        }
        if (patchSupplier.getContactEmail() != null) {
            supplier.setContactEmail(patchSupplier.getContactEmail());
        }
        if (patchSupplier.getCorporatePhone() != null) {
            supplier.setCorporatePhone(patchSupplier.getCorporatePhone());
        }
        if (patchSupplier.getPaymentTermsDays() != null) {
            supplier.setPaymentTermsDays(patchSupplier.getPaymentTermsDays());
        }
        return this.supplierGateway.update(supplier);
    }

    public List<SupplierExpenseReport> findExpenseReport() {
        return this.supplierGateway.findExpenseReport();
    }
}