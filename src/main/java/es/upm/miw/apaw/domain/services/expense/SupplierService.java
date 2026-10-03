package es.upm.miw.apaw.domain.services.expense;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.model.expense.Supplier;
import es.upm.miw.apaw.domain.ports.out.expense.SupplierGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class SupplierService {

    private final SupplierGateway supplierGateway;

    @Autowired
    public SupplierService(SupplierGateway supplierGateway) {
        this.supplierGateway = supplierGateway;
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
}