package es.upm.miw.apaw.domain.services.expense;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.expense.Supplier;
import es.upm.miw.apaw.domain.ports.out.expense.SupplierGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SupplierService {

    private final SupplierGateway supplierGateway;

    @Autowired
    public SupplierService(SupplierGateway supplierGateway) {
        this.supplierGateway = supplierGateway;
    }

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
}